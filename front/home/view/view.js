const recipeId = localStorage.getItem('recipeId');
const memberNameOnHeader = document.getElementById('memberName');
const title = document.getElementById('title');
const createdAt = document.querySelector('.created_at');
const author = document.querySelector('.author');
const description = document.getElementById('description');
const imageContainer = document.querySelector('.image_container');
const token = JSON.parse(localStorage.getItem('user')).token;



window.onload = () => {
    const memberName = JSON.parse(localStorage.getItem('user')).memberName;
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
        title.textContent = data.title;
        createdAt.textContent = data.createdAt;
        author.textContent = `작성자 : ${data.memberName}`;
        description.textContent = data.description;
        
        const imageName = data.imageName;
        console.log(imageName);

        loadImage(imageName);
    })
    .catch(error => {
        console.error("error: ",error);
    })
}

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