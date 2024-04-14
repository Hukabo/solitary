const memberName = JSON.parse(localStorage.getItem('user')).memberName;
const memberNameOnHeader = document.getElementById('memberName');
const articleContainer = document.querySelector('.article_list');
const pageNumber = document.querySelector(".page_num");

const pageData = {
    currentPage: 0,
    totalPages: 0,
}


window.onload = () => {
    memberNameOnHeader.textContent = `${memberName}님 환영합니다!`;


    fetch('http://localhost:8080/recipe/recipes/page/0/10')
    .then(response => {
        console.log(response.status);
        if (!response.ok) {
            throw new Error("server error");
        }
        return response.json();
    })
    .then(data => {
        
        let num = data.response.numberOfElements;
        const totalPages = data.response.totalPages;

        for (let i = 0; i < num; i++) {
            const recipeId = data.response.content[i].recipeId;
            const title = data.response.content[i].title;
            const author = data.response.content[i].member.memberName;

            const li = document.createElement('li');

            li.addEventListener('click', () => {
                localStorage.setItem('recipeId', recipeId);
                window.location.href = "../view/view.html";
            });

            li.innerHTML = `id:${recipeId}<span>제목: <a href="#">${title}</a></span><span>작성자: ${author}</span>`
            
            articleContainer.appendChild(li);
        }

        for (let i = 1; i <= totalPages; i++) {
            const span = document.createElement('span');
            span.textContent = i;
            span.setAttribute("id", `page${i}`);

            pageNumber.appendChild(span);

            span.addEventListener('click', () => {
                articleContainer.innerHTML = "";
                fetch(`http://localhost:8080/recipe/recipes/page/${i-1}/10`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error("server error");
                    }
                    return response.json();
                })
                .then(data => {

                    num = data.response.numberOfElements;

                    for (let i = 0; i < num; i++) {
                        
                        const recipeId = data.response.content[i].recipeId;
                        const title = data.response.content[i].title;
                        const author = data.response.content[i].member.memberName;

                        const li = document.createElement('li');
                        li.addEventListener('click', () => {
                            localStorage.setItem('recipeId', recipeId);
                            window.location.href = "../view/view.html";
                        });
                        li.innerHTML = `id:${recipeId}<span>제목: <a href="#">${title}</a></span><span>작성자: ${author}</span>`
            
                        articleContainer.appendChild(li);
                    }
                })
                .catch(error => {
                    console.log("Error : ", error);
                })
            })
        }
    })
    .catch(error => {
        console.log("Error : ", error);
    })
}