package seedu.club.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.club.commons.core.LogsCenter;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;

public class SingleEventPanel extends UiPart<Region> {
    private static final String FXML = "SingleEventPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SingleEventPanel.class);

    @FXML
    private ListView<Event> eventListView;

    @FXML
    private ListView<Member> memberListView;

    public SingleEventPanel() {
        super(FXML);
    }

    public SingleEventPanel(ObservableList<Event> eventList, ObservableList<Member> memberList) {
        super(FXML);
        memberListView.setItems(memberList);
        memberListView.setCellFactory(listView -> new MemberListViewCell());
        eventListView.setItems(eventList);
        eventListView.setCellFactory(listView -> new EventListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Member} using a {@code MemberCard}.
     */
    class MemberListViewCell extends ListCell<Member> {
        @Override
        protected void updateItem(Member member, boolean empty) {
            super.updateItem(member, empty);

            if (empty || member == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MemberCard(member, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code EventCard}.
     */
    class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCard(event, getIndex() + 1).getRoot());
            }
        }
    }


    public void update(ObservableList<Event> eventList, ObservableList<Member> memberList) {
        memberListView.setItems(memberList);
        memberListView.setCellFactory(listView -> new MemberListViewCell());
        eventListView.setItems(eventList);
        eventListView.setCellFactory(listView -> new EventListViewCell());
    }
}
