// 게시물 정보
const postForm = document.getElementById('postForm');
const postList = document.getElementById('postList');
const postImage = document.getElementById('postImage');
const postTitle = document.getElementById('postTitle');
const postContent = document.getElementById('postContent');
const imageContainer = document.getElementById('image_container');
const categorys = document.querySelectorAll('option');
let recipeId = 0;

// 회원 정보
const memberId = JSON.parse(localStorage.getItem("user")).memberId;
const token = JSON.parse(localStorage.getItem('user')).token;

document.addEventListener("DOMContentLoaded", function() {
    // URL에서 쿼리 매개변수를 가져옵니다.
    const params = new URLSearchParams(window.location.search);
    recipeId = params.get('recipeId');

    getRecipe(recipeId);
});


postForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const foodType = document.getElementById('foodType');

    try {
        const formData = new FormData();

        formData.append("title", postTitle.value);
        formData.append("description", postContent.value);
        formData.append("category", foodType.value);

        const response = await fetch(`http://localhost:8080/recipe/${recipeId}`, {
            method: 'PATCH',
            headers: {
                'Authorization': token,
            },
            body: formData,
        });
        const message = await response.text();

        alert(message);

        window.location.href = "../user/user.html"
    } catch (error) {
        console.error("fail", error);
    }
})

postImage.addEventListener('change', function() {
    console.log(this.files[0]);
    if (this.files[0]) {
        handleFiles(this.files[0])
    }
    else {
        imageContainer.innerHTML = ``;
    }
})

function handleFiles(files) {
    imageContainer.innerHTML = ``;
    const img = document.createElement('img');
    const url = URL.createObjectURL(files);
    img.height = 100;
    img.src = url;
    img.onload = () => URL.revokeObjectURL(this.src);
    imageContainer.appendChild(img);
}

function getRecipe(recipeId) {
    fetch(`http://localhost:8080/recipe/${recipeId}`)
    .then(response => {
        if(!response.ok) {
            throw new Error('error');
        }

        return response.json();
    })
    .then(data => {
        console.log(data);

        postTitle.value = data.title;
        postContent.value = data.description;

        categorys.forEach(c => {
            if(c.textContent === data.category) {
                c.selected = true;
            }
        })
    })
    .catch(error => {
        console.error('error : ', error);
    })
}