package seedu.club.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.club.commons.exceptions.IllegalValueException;
import seedu.club.model.ClubBook;
import seedu.club.model.ReadOnlyClubBook;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;
import seedu.club.model.name.Name;
import seedu.club.model.role.EventRole;

/**
 * An Immutable ClubBook that is serializable to JSON format.
 */
@JsonRootName(value = "clubbook")
class JsonSerializableClubBook {

    public static final String MESSAGE_DUPLICATE_MEMBER = "Members list contains duplicate member(s).";
    public static final String MESSAGE_DUPLICATE_EVENT = "Events list contains duplicate event(s).";
    public static final String MESSAGE_INVALID_MEMBER_EVENT_ROLES = "Member '%s' has invalid event roles: \n%s.";
    public static final String MESSAGE_INVALID_EVENT_ROLE_WITH_REASON = "'%s' — %s";

    private final List<JsonAdaptedMember> members = new ArrayList<>();
    private final List<JsonAdaptedEvent> events = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableClubBook} with the given members and events.
     */
    @JsonCreator
    public JsonSerializableClubBook(@JsonProperty("members") List<JsonAdaptedMember> members,
                                    @JsonProperty("events") List<JsonAdaptedEvent> events) {
        this.members.addAll(members);
        this.events.addAll(events);
    }

    /**
     * Converts a given {@code ReadOnlyClubBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableClubBook}.
     */
    public JsonSerializableClubBook(ReadOnlyClubBook source) {
        members.addAll(source.getMemberList().stream().map(JsonAdaptedMember::new).collect(Collectors.toList()));
        events.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this club book into the model's {@code ClubBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ClubBook toModelType() throws IllegalValueException {
        ClubBook clubBook = new ClubBook();
        for (JsonAdaptedMember jsonAdaptedMember : members) {
            Member member = jsonAdaptedMember.toModelType();
            if (clubBook.hasMember(member)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MEMBER);
            }
            clubBook.addMember(member);
        }
        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType(clubBook.getMemberList());
            if (clubBook.hasEvent(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT);
            }
            clubBook.addEvent(event);
        }

        checkInvalidMemberEventRoles(clubBook);
        return clubBook;
    }

    private static void checkInvalidMemberEventRoles(ClubBook clubBook) throws IllegalValueException {
        List<Event> events = clubBook.getEventList();
        for (Member member : clubBook.getMemberList()) {
            List<String> invalidRoleMessages = new ArrayList<>();

            for (EventRole er : member.getEventRoles()) {
                Name assignedEventName = er.getAssignedTo();

                // Check 1: Event does not exist
                Event assignedEvent = events.stream().filter(e -> e.getName().equals(assignedEventName))
                        .findFirst().orElse(null);

                if (assignedEvent == null) {
                    invalidRoleMessages.add(String.format(MESSAGE_INVALID_EVENT_ROLE_WITH_REASON,
                            er, "event does not exist"));
                    continue;
                }

                // Check 2: Member not in event roster
                boolean isMemberInEventRoster = assignedEvent.getRoster().stream()
                        .anyMatch(m -> m.equals(member));

                if (!isMemberInEventRoster) {
                    invalidRoleMessages.add(String.format(MESSAGE_INVALID_EVENT_ROLE_WITH_REASON,
                            er, "member not in event roster"));
                }

                // Check 3: Event role not found in the event
                if (er.isParticipant()) {
                    continue;
                }
                boolean isRoleInEvent = assignedEvent.getRoles().stream().anyMatch(role -> role.equals(er));

                if (!isRoleInEvent) {
                    invalidRoleMessages.add(String.format(MESSAGE_INVALID_EVENT_ROLE_WITH_REASON,
                            er, "event role not defined in event"));
                }
            }

            if (!invalidRoleMessages.isEmpty()) {
                throw new IllegalValueException(String.format(
                        MESSAGE_INVALID_MEMBER_EVENT_ROLES,
                        member.getName(), invalidRoleMessages
                ));
            }
        }
    }
}
