# order-management
project for marketit

요건 1. API 응답 구현

- 주문 접수처리
- 주문 완료처리

위 두 요건은 API Controller 에서 Service의 같은 메소드를 사용합니다. 파라미터로 Orders PK와 ENUM으로 선언한 Orders의 상태값을 전달합니다.
PK에 해당하는 Orders가 없을 경우 Exception을 발생시키고, 해당하는 Orders가 있을 경우 파라미터로 넘어온 상태값대로 변경합니다.

- 단일 주문조회

Orders PK 를 파라미터로 전달하여 CRUDRepository.findById 메소드를 수행합니다. 결과값이 있을 경우 Orders를 반환하고 없을 경우 Exception을 발생시킵니다.

- 주문 목록조회
JPARepository.findAll 메소드를 수행하여 결과값을 전달합니다.

Orders를 생성하고 수정하는 시간에 대한 값이 필요할 것 같아 BaseTimeEntity를 추가하였습니다.

Orders에 주문한 사용자가 누구인지 판별이 필요할 것 같아 User 를 생성하였고 ManyToOne으로 매핑 하였고 현재는 주석처리 하였습니다.


