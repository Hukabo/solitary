//
const email = document.getElementById('email');
const password = document.getElementById('password');

document.getElementById('login-container').addEventListener('submit', (event) => {
    event.preventDefault();

    const requestData = {
        email: email.value,
        password: password.value,
    }
    console.table(requestData);
    fetch('http://localhost:8080/member/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('서버 오류');
        }
        const token = response.headers.get('Authorization');
        console.log(token);
        localStorage.setItem('token', token);

        return response.text();
    })
    .then(data => {
        alert(data);        

        // 로그인 된 회원 페이지로 이동
    })
    .catch(error => {
        console.log("로그인 실패 ", error);
    })
})