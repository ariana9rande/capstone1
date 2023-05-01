function toggleSidebar() {
    let sidebar = document.querySelector(".leftsidebar");
    if (sidebar.style.display === "none") {
        sidebar.style.display = "block";
    } else {
        sidebar.style.display = "none";
    }
}

function closeSidebar() {
    let sidebar = document.querySelector(".leftsidebar");
    sidebar.style.display = 'none';
}

window.addEventListener('resize', function () {
    if (window.innerWidth >= 1025) {
        document.querySelector('.leftsidebar').style.display = 'block';
    }
});

window.addEventListener('resize', function () {
    if (window.innerWidth <= 1025) {
        document.querySelector('.leftsidebar').style.display = 'none';
    }
});