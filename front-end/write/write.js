const postForm = document.getElementById('postForm');
const postList = document.getElementById('postList');
const postImage = document.getElementById('postImage');
const token = JSON.parse(localStorage.getItem('user')).token;

postForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const postTitle = document.getElementById('postTitle');
    const postContent = document.getElementById('postContent');
    const foodType = document.getElementById('foodType');
    const file = postImage.files[0];
    const memberId = JSON.parse(localStorage.getItem("user")).memberId;

    try {
        const formData = new FormData();

        formData.append("title", postTitle.value);
        formData.append("description", postContent.value);
        formData.append("category", foodType.value);
        formData.append("image", file);

        const response = await fetch(`http://localhost:8080/recipe/${memberId}`, {
            method: 'POST',
            headers: {
                'Authorization': token,
            },
            body: formData,
        });
        const result = await response.json();

        console.log("success", result);

        alert("레시피가 등록되었습니다.");

        window.location.href = "../user/user.html"
    } catch (error) {
        console.error("fail", error);
    }
})

postImage.addEventListener('change', function() {
    handleFiles(this.files)
})

function handleFiles(files) {
    const img = document.createElement('img');
    const url = URL.createObjectURL(files[0]);
    img.height = 100;
    img.src = url;
    img.onload = () => URL.revokeObjectURL(this.src);
    document.getElementById('image_container').appendChild(img);
}