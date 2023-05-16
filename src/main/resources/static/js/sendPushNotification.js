function sendPushNotification(restId, waitId)
{
    // Ajax 요청을 통해 서버로 푸시 알림 전송 요청 보내기
    $.ajax({
        url: `/restId/notice?restId=${restId}&waitId=${waitId}`,
        method: 'POST',
        success: function (response)
        {
            // 푸시 알림 전송이 완료되었다는 메시지 또는 다른 동작 수행
            console.log('푸시 알림 전송 완료');
            // 원하는 동작을 수행할 타임리프 코드 추가 가능
        },
        error: function (error)
        {
            // 오류 처리
            console.log('푸시 알림 전송 오류:', error);
        }
    });
}
