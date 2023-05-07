package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Command {
    UPLOAD("0"),
    VIEWITEM("1"),
    INTOCART("2"),
    VIEWCART("2"),
    BUY("3"),
    QUIT("Q");

    private static final String INVALID_COMMAND_MESSAGE = "올바르지 않은 커맨드입니다.";

    private final String command;


    public static Command from(String command) {
        return Arrays.stream(values())
                .filter(controllerCommand -> controllerCommand.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_MESSAGE));
    }


    public boolean isNotQuit() {
        return this != QUIT;
    }
}