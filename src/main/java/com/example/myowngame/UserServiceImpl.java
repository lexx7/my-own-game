package com.example.myowngame;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    private SimpMessagingTemplate messagingTemplate;
    private static Map<String, Instant> users = new HashMap<>();

    @Override
    public String add(String username) {
        if (users.containsKey(username)) {
            return "redirect:/index?error=This user already exists";
        }
        users.put(username, null);
        try {
            return "redirect:/button?username=" + URLEncoder.encode(username, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "redirect:/index?error=" + e.getMessage();
        }
    }

    @Override
    public String remove(String username) {
        users.remove(username);
        return "redirect:/index?logout";
    }


    @Override
    public void clearPush() {
        for (String username: users.keySet()) {
            users.put(username, null);
        }
    }

    @Override
    public void changeStateGame(boolean isPush) {
        if (isPush) {
            clearPush();
        }
        messagingTemplate.convertAndSend("/topic/statePush", new StatePushMessage(isPush));
    }

    @Override
    public void clearAll() {
        users = new HashMap<>();
    }

    @Override
    public void push(String username) {
        if (users.containsKey(username) && users.get(username) == null) {
            users.put(username, Instant.now());
        }
    }

    @Override
    public List<Person> list() {
        return users.entrySet().stream()
                .filter(user -> user.getValue() != null)
                .sorted((o1, o2) -> Long.compare(o1.getValue().toEpochMilli(), o2.getValue().toEpochMilli()))
                .map(user -> {
                    Instant shanghai = Instant.ofEpochMilli(user.getValue().toEpochMilli());
                    OffsetDateTime o = OffsetDateTime.ofInstant(shanghai, ZoneId.systemDefault());
                    DateTimeFormatter dtf = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
                    return new Person(user.getKey(), dtf.format(o));
                }).collect(Collectors.toList());
    }
}
