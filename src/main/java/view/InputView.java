package view;

import domain.dto.CommandDto;
import domain.dto.IntoCartDto;
import domain.dto.UploadDto;
import util.Print;

import java.util.Scanner;

public class InputView {
    private final Scanner sc = new Scanner(System.in);

    // 기능 구현
    public CommandDto readCommand() {
        Print.gameStart();

        String command = sc.nextLine().toUpperCase();
        return new CommandDto(command);
    }

    public UploadDto readUpload() {
        Print.categoryType();
        String uploadInfo = sc.nextLine();

        return new UploadDto(uploadInfo);
    }

    public IntoCartDto readIntoCartItem() {
        Print.intoCartItem();
        String intoCartDto = sc.nextLine();

        return new IntoCartDto(intoCartDto);
    }

    /*public int readMoney() {
        Print.putMoney();
        String money = sc.nextLine();

        return Integer.parseInt(money);
    }*/
}
