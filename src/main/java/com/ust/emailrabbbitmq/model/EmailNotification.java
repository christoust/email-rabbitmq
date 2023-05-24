package com.ust.emailrabbbitmq.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmailNotification {
    private String to;
    private String subject;
    private String content;
}
