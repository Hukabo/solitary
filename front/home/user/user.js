const memberName = JSON.parse(localStorage.getItem('user')).memberName; // 회원 정보를 로컬스토리지에서 꺼내옴
const memberNameOnHeader = document.getElementById('memberName');
const articleContainer = document.querySelector('.article_list');
const pageNumber = document.querySelector(".page_num");

const pageData = { // 레시피 전체 목록을 가져오기 위한 페이지 정보
    currentPage: 0,
    totalPages: 0,
}


window.onload = () => { // 페이지 로드 시,
    memberNameOnHeader.textContent = `${memberName}님 환영합니다!`;


    fetch('http://localhost:8080/recipe/recipes/page/0/10') // 레시피 전체 목록을 10개 단위로 요청
    .then(response => {

        if (!response.ok) {
            throw new Error("server error");
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        let num = data.response.numberOfElements; // 해당 응답 페이지의 레시피 갯수
        const totalPages = data.response.totalPages; // 전체 페이지 수

        for (let i = 0; i < num; i++) {
            const recipeId = data.response.content[i].recipeId;
            const title = data.response.content[i].title;
            const author = data.response.content[i].member.memberName;

            const li = document.createElement('li');

            li.addEventListener('click', () => {
                localStorage.setItem('recipeId', recipeId); // 해당 레시피를 id로 조회하기 위해 로컬스토리지에 저장
                window.location.href = "../view/view.html";
            });

            li.innerHTML = `<span>레시피 제목 :  <a href="#">${title}</a></span><span>작성자: ${author}</span>`
            
            articleContainer.appendChild(li);
        }

        for (let i = 1; i <= totalPages; i++) { // user.html 하단에 페이지 숫자 부여
            const span = document.createElement('span');
            span.textContent = i;
            span.setAttribute("id", `page${i}`);

            pageNumber.appendChild(span);

            span.addEventListener('click', () => { // 해당 페이지의 레시피 목록 요청 이벤트 등록
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

                    for (let i = 0; i < num; i++) { // 위 코드와 동일하게 해당 번호 클릭 시, 해당 페이지의 레시피 목록 렌더링
                        
                        const recipeId = data.response.content[i].recipeId;
                        const title = data.response.content[i].title;
                        const author = data.response.content[i].member.memberName;

                        const li = document.createElement('li');
                        li.addEventListener('click', () => {
                            localStorage.setItem('recipeId', recipeId);
                            window.location.href = "../view/view.html";
                        });
                        li.innerHTML = `<span>레시피 제목 :  <a href="#">${title}</a></span><span>작성자: ${author}</span>`
            
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