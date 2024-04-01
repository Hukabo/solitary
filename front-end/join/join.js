// 회원가입 페이지에서 회원가입시, 회원 데이터를 서버로 전송해서 db에 저장한 다음, 성공했을 경우, 홈 화면으로 리다이렉팅 혹은 성공 페이지,,,
const memberName = document.getElementById('name');
const email = document.getElementById('email');
const password = document.getElementById('password');


document.getElementById("signupForm").addEventListener('submit', (event) => {
    event.preventDefault();
    

    const requestData = {
        memberName: memberName.value,
        email: email.value,
        password: password.value,
    };
    console.log(requestData)
    fetch('http://localhost:8080/member', {
        method: 'POST',
        headers: {
            'Content-Type':  'application/json',
        },
        body: JSON.stringify(requestData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('서버 오류')
        }
        return response.json();
    })
    .then(data => {
        if (data.status === 404) {
            alert("이미 존재하는 회원입니다.");
            return;
        }
        alert(`${data.memberName}님 환영합니다!`);
        window.location.href="../home/home.html"
    })
    .catch(error => {
        console.log("회원가입 실패: ", error);
    })
});