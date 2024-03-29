package org.sparta.backmaterialspring.payment.facade;

import org.sparta.backmaterialspring.auth.entity.User;
import org.sparta.backmaterialspring.payment.dto.CreateOrderDto;
import org.sparta.backmaterialspring.payment.dto.OrderInfoDto;

public interface PaymentFacade {
    /**
     * 주문 정보를 조회한다
     * @param orderId order entity pk
     * @return 간단한 주문 정보가 담긴 dto
     * @throws Exception exception
     */
    OrderInfoDto getOrderInfo(Long orderId) throws Exception;
    /**
     * 주문을 생성한다.
     * @param createOrderDto 주문 생성을 위한 dto 객체
     * @return 생성된 주문
     * @throws Exception exception
     */
    Long initOrder(CreateOrderDto createOrderDto) throws Exception;

    /**
     * 주문을 대기상태로 만든다.
     * @param orderId order entity pk
     * @return order entity
     * @throws Exception exception
     */
    Long prepareOrder(Long orderId) throws Exception;

    /**
     * 주문을 완료한다.
     * @param orderId order entity pk
     * @return order entity
     * @throws Exception exception
     */
    Long completeOrder(Long orderId, User user) throws Exception;

    /**
     * 대기상태의 주문을 취소한다.
     * @param orderId order entity pk
     * @return order entity
     * @throws Exception exception
     */
    Long undoOrder(Long orderId) throws Exception;
}
