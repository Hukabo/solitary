const signUpBtn = document.getElementById("signUpBtn");
const signInBtn = document.getElementById("signInBtn");
const headerMenu = document.querySelector('.header_menu');


const attatchNameListToHeader = (memberName) => {
    
    const li = document.createElement('li');

    li.textContent = `${memberName}님`;
    li.classList.add('header_menu-item');

    li.addEventListener('click', () => {
        alert("회원 정보 페이지 구현");
    });

    headerMenu.appendChild(li);
};

const attatchWriteListToHeader = () => {

    const li = document.createElement('li');

    li.textContent = "글쓰러가기";
    li.classList.add('header_menu-item');

    li.addEventListener('click', () => {
        window.location.href = '../write/write.html';
    });

    headerMenu.appendChild(li);
};

const attatchRecipesListToHeader = () => {

    const li = document.createElement('li');

    li.textContent = "글목록 보기";
    li.classList.add('header_menu-item');

    li.addEventListener('click', () => {
        window.location.href = '../user/user.html';
    });

    headerMenu.appendChild(li);
};

window.onload = () => {
    if (localStorage.getItem('user')) {
        const memberName = JSON.parse(localStorage.getItem('user')).memberName;

        headerMenu.innerHTML = "";
        
        attatchNameListToHeader(memberName);
        attatchWriteListToHeader();
        attatchRecipesListToHeader();

        signUpBtn.remove();
        signInBtn.textContent = "Go To Write";
        document.querySelector('.cta_textBox p:nth-of-type(2)').textContent = "Have a good recipe";
        signInBtn.addEventListener('click', () => {
            window.location.href = "../write/write.html";
        });

    } else {

        signUpBtn.addEventListener('click', () => {
            window.location.href = "../join/join.html";
        });
        
        signInBtn.addEventListener('click', () => {
            window.location.href = "../login/login.html";
        });
    }
};

