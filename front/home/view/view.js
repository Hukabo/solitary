// 유저 및 레시피 정보
const memberName = JSON.parse(localStorage.getItem('user')).memberName;
const recipeId = localStorage.getItem('recipeId');
const memberId = JSON.parse(localStorage.getItem('user')).memberId;
const token = JSON.parse(localStorage.getItem('user')).token;
// 데이터 렌더링 타겟
const memberNameOnHeader = document.getElementById('memberName');
const title = document.getElementById('title');
const createdAt = document.querySelector('.created_at');
const author = document.querySelector('.author');
const description = document.getElementById('description');
const imageContainer = document.querySelector('.image_container');
// 댓글 요소들
const commentForm = document.querySelector('.comment-form');
const commentsContainer = document.querySelector('.comments-container');
// request버튼
const modifyBtn = document.getElementById('btn-modify');
const deleteBtn = document.getElementById('btn-delete');

window.onload = () => {
    getRecipe();
};

modifyBtn.addEventListener('click', () => {
    window.location.href = `../edit/edit.html?recipeId=${recipeId}`;
});
deleteBtn.addEventListener('click', () => {
    const isDelete = confirm("삭제하시겠습니까?");

    if(isDelete) {
        deleteRecipe(recipeId);
    }
});

function loadImage(imageName) {
    fetch(`http://localhost:8080/image/file/${imageName}`, {
        method: 'GET',
        headers: {
            'Authorization': token,
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("image loading fail.")
        }
        return response.blob();
    })
    .then(blob => {
        const img = document.createElement('img');
        img.classList.add('recipe_image');
        const imageUrl = URL.createObjectURL(blob);
        img.src = imageUrl;
        imageContainer.appendChild(img);
    })
    .catch(error => {
        console.error("error", error);
    })
}

function loadComments(recipeId) {
    fetch(`http://localhost:8080/comment/${recipeId}`, {
        method: 'GET',
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("서버 에러");
        }

        return response.json();
    })
    .then(data => {
        console.log(data);
        const comments = data.comments;
        commentsContainer.innerHTML = ``;
        
        for (let comment of comments) {
            commentsContainer.innerHTML += 
            `
            <div>
                <span>${comment.author}</span>
                <span>${comment.content}</span>
            </div>
            `
        }
    })
    .catch(error => {
        console.error("Error : ", error)
    })
}

commentForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const commentInput = document.getElementById('comment-input');
    
    const requestData = {
        "content": commentInput.value,
    }

    fetch(`http://localhost:8080/comment/${memberId}/${recipeId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestData)
    })
    .then(response => {
        if(!response.ok) {
            throw new Error("서버 에러")
        }

        return response.json();
    })
    .then(data => {
        console.log(data);
        location.reload();
    })
    .catch(error => {
        console.error("Error : ", error);
    })
})

function getRecipe() {

    memberNameOnHeader.textContent = `${memberName}님`;

    fetch(`http://localhost:8080/recipe/${recipeId}`)
    .then(response => {
        if (!response.ok) {
            throw new Error("recipe loading fail.");
        }
        return response.json();
    })
    .then(data => {
        console.log(data);

        const yyyymmdd = data.createdAt.substring(0, 10);
        const hour = data.createdAt.substring(11, 16);
        const time = yyyymmdd + " " + hour;

        title.textContent = data.title;
        createdAt.textContent = time;
        author.textContent = `작성자 : ${data.memberName}`;
        description.textContent = data.description;
        
        const imageName = data.imageName;
        console.log(imageName);

        if(data.memberName == memberName) { // 작성자와 로그인한 회원과 같다면
            document.querySelector('.btn-container').classList.remove('display-none');
        }

        if(imageName !== '') {
            loadImage(imageName);
        }
        
        loadComments(recipeId);
    })
    .catch(error => {
        console.error("error: ",error);
    })
};

function deleteRecipe(recipeId) {

    fetch(`http://localhost:8080/recipe/${recipeId}`, {
        method: 'DELETE',
    })
    .then(response => {
        if(!response.ok) {
            throw new Error('error');
        }

        return response.text();
    }) 
    .then(data => {
        alert(data);

        window.location.href = '../user/user.html'
    })
    .catch(error => {
        console.error('error : ', error);
    })
};
