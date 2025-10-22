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
    private StackPane eventCard;

    @FXML
    private ListView<Member> memberListView;

    public SingleEventPanel() {
        super(FXML);
    }

    public SingleEventPanel(Event event, ObservableList<Member> memberList) {
        super(FXML);
        memberListView.setItems(memberList);
        memberListView.setCellFactory(listView -> new MemberListViewCell());
        eventCard.getChildren().add(new EventCard(event, 1).getRoot());
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

    public void update(Event event, ObservableList<Member> memberList) {
        memberListView.setItems(memberList);
        memberListView.setCellFactory(listView -> new MemberListViewCell());
        eventCard.getChildren().clear();
        Region eventCardRoot = new EventCard(event, -1).getRoot();
        eventCard.getChildren().add(new EventCard(event, -1).getRoot());
        eventCardRoot.applyCss();
    }
}
