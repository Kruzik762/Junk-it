package com.junkit.trade.service;


import com.junkit.trade.MessageDto;
import com.junkit.trade.domain.Item;
import com.junkit.trade.domain.Message;
import com.junkit.trade.domain.User;
import com.junkit.trade.repository.MessageRepository;
import com.junkit.trade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {


    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;

    public List<Message> findAllByReceiverUserId (Long userId) {
        List<Message> messagesList = messageRepository.findAllByReceiverUserId(userId);
        return messagesList;
    }

    public void createDirectMessage(User loggedInUser, Long userId, Message newMessage) {
        User user = userService.findById(userId);
        newMessage.setReceiver(user);
        newMessage.setSender(loggedInUser);
        newMessage.setTimeSent(LocalDateTime.now());
        save(newMessage);
    }

    public void messageItemOwner(User loggedInUser, Long itemId, Message newMessage) {
        Item itemInquiredOn = itemService.findById(itemId);
        User itemOwner = userService.findById(itemInquiredOn.getUser().getUserId());
        newMessage.setItem(itemInquiredOn);
        newMessage.setReceiver(itemOwner);
        newMessage.setSender(loggedInUser);
        newMessage.setTimeSent(LocalDateTime.now());
        save(newMessage);
    }


    public MessageDto createReplyMessage(MessageDto messageDto) {
        Message newMessage = new Message();
        Message repliedToMessage = findById(messageDto.getReplyingToId());
        repliedToMessage.setReplied(true);
        User receiver = userService.findById(messageDto.getReceiverId());
        User sender = userService.findById(messageDto.getSenderId());
        repliedToMessage.setRepliedWithMessage(messageDto.getMessageText());
        newMessage.setReceiver(receiver);
        newMessage.setSender(sender);
        newMessage.setMessageText(messageDto.getMessageText());
        newMessage.setTimeSent(LocalDateTime.now());
        save(newMessage);
        messageDto.setMessageId(newMessage.getMessageId());
        System.out.println(messageDto.getMessageId());
        return messageDto;

    }

//    public MessageDto getRepliedMessage(User loggedInUser) {
//
//    }

    public Message save (Message message) {
        return messageRepository.save(message);
    }

    public Message findById(Long messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    public void deleteMessageById(Long messageId) {
        messageRepository.delete(findById(messageId));
    }



}
