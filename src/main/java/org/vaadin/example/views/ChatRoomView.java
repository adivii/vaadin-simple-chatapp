package org.vaadin.example.views;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.vaadin.example.data.entity.User;
import org.vaadin.example.data.service.UserService;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "/chat")
@PageTitle("Chat Room")
public class ChatRoomView extends HorizontalLayout {

    private UserService userService;

    public ChatRoomView(UserService userService) {
        this.userService = userService;

        Button backButton = new Button("Back");

        backButton.addClickListener(e -> {
            backButton.getUI().ifPresent(ui -> ui.navigate(MainView.class));
        });

        setSizeFull();
        add(backButton, getChatLayout());
    }

    private Component getChatLayout() {
        // Create layout for the chat room
        MessageList chatList = new MessageList();
        MessageInput chatInput = new MessageInput();

        Optional<User> currentUser;
        currentUser = userService.getUser("adivii");

        chatInput.addSubmitListener(submit -> {
            // Retrieve user data
            String name = "Rei - Temp";

            if(currentUser.isPresent()) {
                User temp = currentUser.get();
                name = temp.getFirstName().concat(" ").concat(temp.getLastName());
            }

            // Create chat bubble
            MessageListItem newChat = new MessageListItem(
                submit.getValue(), Instant.now(), name
            );
            List<MessageListItem> items = new ArrayList<>(chatList.getItems());
            items.add(newChat);
            chatList.setItems(items);
        });

        VerticalLayout chatLayout = new VerticalLayout(chatList, chatInput);
        chatLayout.expand(chatList);
        chatList.setWidthFull();
        chatInput.setWidthFull();
        // chatLayout.setSizeFull();
        chatLayout.setAlignItems(Alignment.END);

        return chatLayout;
    }
}
