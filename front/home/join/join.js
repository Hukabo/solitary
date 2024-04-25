

document.getElementById("signupForm").addEventListener('submit', (event) => {
    event.preventDefault();
    
    const memberName = document.getElementById('name');
    const email = document.getElementById('email');
    const password = document.getElementById('password');

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
        alert(`회원가입 완료! 로그인 해주세요!`);
        window.location.href="../home.html"
    })
    .catch(error => {
        console.log("회원가입 실패: ", error);
    })
});