package controller;

import domain.Command;
import domain.dto.CommandDto;
import domain.dto.IntoCartDto;
import domain.dto.UploadDto;
import service.KioskService;
import util.Retry;
import view.InputView;

import java.util.HashMap;
import java.util.Map;

public class KioskController {
    private final InputView inputView = new InputView();
    private final KioskService kioskService;
    private Map<Command, Runnable> service = new HashMap<>();

    // map <key, value> key - value
    public KioskController(KioskService kioskService) {
        this.kioskService = kioskService;
        service.put(Command.UPLOAD, this::upload);
        service.put(Command.VIEWITEM, this::viewItem);
        service.put(Command.INTOCART, this::intoCart);
        service.put(Command.VIEWCART, this::viewCart);
        service.put(Command.BUY, this::payed);
    }

    public void run() {
        Command command = readCommand();
        while (command.isNotQuit()) {
            try {
                service.get(command).run();
            } catch (IllegalArgumentException e) {
            }
            command = readCommand();
        }
    }


    public Command readCommand() {
        try {
            CommandDto dto = Retry.execute(inputView::readCommand);
            return Command.from(dto.getCommand());
        } catch (IllegalArgumentException e) {

        }
        return null;
    }
    // 상품을 등록하는 API 구현하시오
    public void upload() {
        UploadDto dto = inputView.readUpload();
        kioskService.upload(dto);
    }

    // 등록된 상품을 카테고리별로 출력해주는 API를 구현하시오
    public void viewItem() {
        kioskService.viewItem();
    }

    // 상품을 장바구니에 넣는 API 기능을 구현하시오
    public void intoCart() {
        IntoCartDto dto = inputView.readIntoCartItem();
        kioskService.addCart(dto);
    }
    // 현재 장바구니에 들어있는 상품들을 보여주는 API 기능을 구현하시오.

    public void viewCart() {
        kioskService.viewCart();
    }
    // 장바구니에 있는 상품 전체들을 결제하기 위해 얼마가 필요한지 알려주는 API 기능을 구현하시오.
    // 결과를 알려준 후 프로그램 종료합니다.

    public void payed() {
        // 합산 금액
        kioskService.pay();
    }

    /*// 결제하기 API - 돈 넣기
    public void putMoney() {

    }


    // 결제 후 잔액 반환 API
    public void payAndReturn() {

    }*/
}
