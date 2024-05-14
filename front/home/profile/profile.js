const memberId = JSON.parse(localStorage.getItem('user')).memberId;
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const memberNameInput = document.getElementById('memberName');
const updateMemberForm = document.querySelector('.input-container');
const logout = document.getElementById('logout');
const recipes = document.getElementById('recipes');


window.onload = () => {

    loadMember(memberId);
    getRecipes(memberId);
}

function loadMember(memberId) {
    fetch(`http://localhost:8080/member/${memberId}`)
    .then(response => {
         if (!response.ok) {
            throw new Error("server error");
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        emailInput.value = data.email;
        memberNameInput.value = data.memberName;

    })
    .catch(error => {
        console.error('error : ', error);
    })
}

function updateMember() {
    const patchMember = {
        memberId: memberId,
        memberName: memberNameInput.value,
        password: passwordInput.value,
    }
    console.table(patchMember);

    fetch(`http://localhost:8080/member`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(patchMember),
    })
    .then(response => {
        if(!response.ok) {
            throw new Error("server error");
        }
        return response.json();
    })
    .then(data => {
        let user = JSON.parse(localStorage.getItem('user'));
        user.memberName = data.memberName;
        localStorage.setItem('user', JSON.stringify(user));
        alert("수정되었습니다!")
        location.reload();
    })
    .catch(error => {
        console.error('error : ', error);
    })
}

updateMemberForm.addEventListener('submit',(event) => {
    event.preventDefault();

    updateMember();
});

function getRecipes(memberId) {

    fetch(`http://localhost:8080/member/recipe/${memberId}`)
    .then(response => {
        if(!response.ok) {
            throw new Error("error")
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        const count = data.recordCount;

        for(let i=0; i<count; i++) {
            const recipe = data.response[i];

            const tr = document.createElement('tr');
            tr.addEventListener('click', () => {
                localStorage.setItem('recipeId', recipe.recipeId);
                window.location.href = "../view/view.html";
            });

            tr.innerHTML += `
                <td>${recipe.recipeId}</td>
                <td>${recipe.title}</td>
                <td>${recipe.createdAt}</td>`;
            
            recipes.appendChild(tr);
        };
    })
    .catch(error => {
        console.error('error : ', error);
    })
}

logout.addEventListener('click', () => {
    const logout = confirm("로그아웃 하시겠습니까?");

        if(logout) {
            localStorage.removeItem('user');
            localStorage.removeItem('token');
            window.location.href = "../home.html";
        }
});