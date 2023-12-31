# 기능 목록

## 도메인 로직
- [x] 할인 금액의 합계를 구한다.
  - 총 주문 금액 10,000원 이상부터 적용
  - [x] 디데이 할인
    - 2023.12.1 ~ 2023.12.25
    - 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
  - [x] 평일 할인
    - 일요일 ~ 목요일
    - 디저트 메뉴 당 2,023원 할인
  - [x] 주말 할인
    - 금요일, 토요일
    - 메인 메뉴 당 2,023원 할인
  - [x] 특별할인
    - 이벤트 달력에 별이 있으면 (매주 일요일, 크리스마스)
    - 1,000원 할인
    
- [x] 주문 메뉴를 구한다.
  - 음료만 주문할 수 없다.
  - 메뉴는 한 번에 최대 20개까지만 주문할 수 있다.
- [x] 총주문 금액을 구한다.
- [x] 총주문 금액이 12만원 이상이면 샴페인 1개를 증정한다.
  - [x] 총주문 금액인지 12만원 이상인지 확인한다.
- [x] 혜택 내역을 구한다.
  - 혜택 내역 = 할인 내역 + 증정 이벤트 내역 
- [x] 총혜택 금액을 구한다.
  - 총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
- [x] 혜택 금액에 따른 12월 이벤트 배지를 부여한다.
  - 5천 원 이상: 별
  - 1만 원 이상: 트리
  - 2만 원 이상: 산타
- [x] 할인 후 예상 결제 금액을 구한다.
  - 할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액

## 입력
- [x] 예상 방문 날짜를 입력받는다.
  - 방문할 날짜는 1 이상 31 이하의 숫자로만 입력받는다.
  - 1 이상 31 이하의 숫자가 아닌 경우, "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."라는 메시지를 출력한다.
- [x] 주문할 메뉴와 메뉴의 개수를 입력받는다.
  - 메뉴와 개수는 하이픈(-)으로 구분하며, 각 메뉴는 쉼표(,)로 구분한다.
  - 메뉴1-개수,메뉴2-개수,메뉴3-개수 ...
  - 메뉴 형식이 예시와 다른 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 출력한다.
  - 메뉴판에 없는 메뉴를 입력하는 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 출력한다.
  - 메뉴의 개수는 1 이상의 숫자만 입력되도록 한다. 이외의 입력값은 "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 출력한다.
  - 중복 메뉴를 입력한 경우(e.g. 시저샐러드-1,시저샐러드-1), "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 출력한다.
  - 에러 메시지를 출력한 후, 유효한 입력을 받을 때까지 입력받는다.

## 출력
- [x] 시작 메시지를 출력한다.
  - 안녕하세요! 우테코 식당 12월 이벤트 플래너입니다. 
- [x] 결과 출력 시작 메시지를 출력한다.
  - {날짜}에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
- [x] 주문 메뉴 내역을 출력한다.
- [x] 할인 전 총주문 금액을 출력한다.
- [x] 증정 메뉴를 출력한다.
  - 증정 이벤트에 해당하지 않는 경우, 증정 메뉴 "없음"으로 출력한다.
- [x] 혜택 내역을 출력한다.
  - 고객에게 적용된 이벤트 내역만 출력한다.
  - 적용된 이벤트가 하나도 없다면 혜택 내역 "없음"으로 출력한다.
- [x] 내역 없음을 출력한다. 
- [x] 총혜택 금액을 출력한다.
- [x] 할인 후 예상 결제 금액을 출력한다. 
- [x] 12월 이벤트 배지를 출력한다.
  - 이벤트 배지가 부여되지 않는 경우, "없음"으로 출력한다. 

---
# 예외 목록

## 입력
- [x] 예상 방문 날짜가 숫자가 아닌 경우
- [x] 예상 방문 날짜를 입력하지 않은 경우
- [x] 주문할 메뉴와 메뉴의 개수를 입력하지 않은 경우

## 도메인
- [x] 예상 방문 날짜가 1 이상 31 이하가 아닌 경우 - EventDate
- [x] 메뉴 형식이 맞지 않는 경우 - OrderParser
- [x] 메뉴의 개수가 숫자가 아닌 경우 - OrderParser
- [x] 메뉴의 개수가 1 이상이 아닌 경우 - OrderParser
- [x] 메뉴판에 없는 메뉴를 입력하는 경우 - Menu
- [x] 중복 메뉴를 입력하는 경우 - Orders
- [x] 메뉴를 20개 초과 주문하는 경우 - Orders
- [x] 음료만 주문한 경우 - Orders