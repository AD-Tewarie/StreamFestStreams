package com.example.streamfeststreams.fragments;

import com.google.gson.Gson;

import java.util.List;

public class LiveChatMessages {
    //fields
    Gson gson = new Gson();
    private String nextPageToken;
    private String id;
    private List<Items> items;

    // constructor
    public LiveChatMessages(Snippet snippet) {
        snippet.textMessageDetails.messageText = "Send from the StreamFest app";
    }

    //getters
    public String getNextPageToken() {
        return nextPageToken;
    }

    public String getId() {
        return id;
    }

    public List<Items> getItems() {
        return items;
    }

    class Items {
        private Snippet snippet;
        private AuthorDetails authorDetails;

        public Snippet getSnippet() {
            return snippet;
        }

        public AuthorDetails getAuthorDetails() {
            return authorDetails;
        }
    }

    class Snippet {
        String displayMessage;
        String type = "textMessageEvent";
        String liveChatId;
        TextMessageDetails textMessageDetails;
    }

    class AuthorDetails {
        private String displayName;

        public String getDisplayName() {
            return displayName;
        }
    }

    class TextMessageDetails {
        private String messageText;

        public String getMessageText() {
            return messageText;
        }
    }

}
