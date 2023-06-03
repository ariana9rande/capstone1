document.addEventListener("DOMContentLoaded", function () {

    const topnavItems = document.querySelectorAll('.topnav-item');

    // 클릭 이벤트 리스너 추가하기
    topnavItems.forEach((item) => {
        item.addEventListener('click', () => {
            // 클릭한 탭에 스타일링 클래스 추가하기
            item.classList.add('topnav-item--selected');
            // 나머지 탭에 스타일링 클래스 제거하기
            topnavItems.forEach((otherItem) => {
                if (otherItem !== item) {
                    otherItem.classList.remove('topnav-item--selected');
                }
            });
        });
    });

    function handleResize() {
        if (window.innerWidth <= 768) {
            let currentPageUrl = '/' + window.location.href.split('/').pop();
            for (let i = 0; i < topnavItems.length; i++) {
                const item = topnavItems[i];
                const href = item.getAttribute('href');
                // alert("item.getAttribute('href'): " + href);
                // alert("currentPageUrl: " + currentPageUrl);
                if (href === currentPageUrl) {
                    item.classList.add('topnav-item--selected');
                } else {
                    item.classList.remove('topnav-item--selected');
                }
            }
        } else {
            topnavItems.forEach((item) => {
                item.classList.remove('topnav-item--selected');
            });
        }
    }

    // 초기에 실행해주기
    handleResize();

    // 리사이즈 이벤트 리스너 추가하기
    window.addEventListener('resize', handleResize);
});
