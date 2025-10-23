package seedu.club.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.club.model.member.Member;
import seedu.club.model.role.EventRole;

/**
 * A UI component that displays information of a {@code Member}.
 */
public class MemberCard extends UiPart<Region> {

    private static final String FXML = "MemberListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Member member;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private FlowPane memberRoles;
    @FXML
    private FlowPane eventRoles;

    /**
     * Creates a {@code MemberCode} with the given {@code Member} and index to display.
     */
    public MemberCard(Member member, int displayedIndex) {
        super(FXML);
        this.member = member;
        id.setText(displayedIndex + ". ");
        name.setText(member.getName().fullName);
        phone.setText(member.getPhone().value);
        email.setText(member.getEmail().value);
        member.getMemberRoles().stream()
                .sorted(Comparator.comparing(memberRole -> memberRole.roleName))
                .forEach(memberRole -> memberRoles.getChildren().add(new Label(memberRole.roleName)));
        member.getEventRoles().stream()
                .sorted(Comparator.comparing(eventRole -> eventRole.roleName))
                .forEach(eventRole -> eventRoles.getChildren().add(new Label(eventRole.roleName)));
    }
}
