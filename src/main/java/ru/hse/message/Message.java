package ru.hse.message;

import lombok.Getter;

/** Представляет сообщение, которое может обработать агент */
public class Message {
  private static int messagesCount = 0;
  @Getter private final int id;

  public Message() {
    this.id = ++messagesCount;
  }
}
