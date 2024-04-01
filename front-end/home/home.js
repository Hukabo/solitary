const signUpBtn = document.getElementById("signUpBtn");
const signInBtn = document.getElementById("signInBtn");

signUpBtn.addEventListener('click', () => {
    window.location.href="../join/join.html"
});

signInBtn.addEventListener('click', () => {
    window.location.href="../login/login.html"
});