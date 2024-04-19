# Solitary's Recipe

<br />

> ## Project Status : 진행중...

배포링크 : <http://hukabo.click/> (작성 페이지에서 이미지 파일은 현재 jpeg만 등록 가능합니다..!)

---

 ## 🙇‍♂️ Project Outline :
    - 온라인 부트캠프(백엔드 과정) 수료 후, 전체적인 웹 흐름을 알고 싶어서 시작하게 된 프로젝트.
    - 혼자 사는 자취생부터, 직장인 등 다양하게 자신만의 레시피를 공유하는 사이트.
    - 아침을 간편하게 해결할 수 있는 레시피와
    - 자신만이 알고 있는 음식 꿀조합 등을
    - 서로서로 공유 할 수 있는 사이트

---

<!-- 여기에 사이트 동작 과정 gif로 보여주기 -->

## ⚙️ Envirement :
| Git | Github | IntelliJ | VS Code |
| :---: | :---: | :---: | :---: |
| <img alt="git logo" src="https://git-scm.com/images/logos/logomark-orange@2x.png" width="65" height="65" > | <img alt="github logo" src="https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png" width="65" height="65"> | <img alt="intellij logo" src="https://w7.pngwing.com/pngs/702/907/png-transparent-intellij-idea-integrated-development-environment-computer-software-jetbrains-java-others-miscellaneous-angle-text-thumbnail.png" width="65" height="65"> | <img alt="vscode logo" src="https://w7.pngwing.com/pngs/905/947/png-transparent-microsoft-visual-studio-code-alt-macos-bigsur-icon-thumbnail.png" width="65" height="65"> |

## 🖥️ Front-End : 
| HTML5 | CSS | JavaScript |
| :---: | :---: | :---: |
| <img alt="html5 logo" src="https://w7.pngwing.com/pngs/201/90/png-transparent-logo-html-html5-thumbnail.png" width=65 height=65> | <img alt="css logo" src="https://w7.pngwing.com/pngs/696/424/png-transparent-logo-css-css3-thumbnail.png" width=65 height=65> | <img alt="js logo" src="https://w7.pngwing.com/pngs/1019/456/png-transparent-js-logo-logos-logos-and-brands-icon-thumbnail.png" width=65 height=65> |

### Comment : 
HTML5로 기본적인 특을 만들고 CSS의 flex box 등을 활용하여 레이아웃을 구성하였습니다. <br />
JavaScript로 동적인 화면을 구성해 주었고, Fetch API로 서버와 비동기적으로 HTTP 통신을 해주었습니다.

---

## 🛠️ Back_End : 
| Java17 | Spring Boot 3.2.2| Spring JPA Data | PostgresSQL | Spring Security | JWT | AWS | Linux |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| <img src="https://techstack-generator.vercel.app/java-icon.svg" alt="icon" width="65" height="65" /> | <img alt="spring logo" src="https://w7.pngwing.com/pngs/6/979/png-transparent-spring-framework-computer-icons-spring-web-flow-java-advancement-leaf-logo-grass-thumbnail.png" width=65 height=65 > | <img alt="spring jpa data logo" src="https://walczak.it/application/files/5615/5947/5300/spring-data.png" width=65 height=65> | <img alt="postgreSQL logo" src="https://w7.pngwing.com/pngs/898/616/png-transparent-postgresql-macos-database-app-store-others-snout-electric-blue-mac-thumbnail.png" width=65 height=65> | <img alt="spring security logo" src="https://miro.medium.com/v2/resize:fit:4800/format:webp/1*8X26HYxkQ1YPkrW2oliKpw.png" width=65 height=65> | <img alt="aws logo" src="https://seeklogo.com/images/J/json-web-tokens-jwt-io-logo-C003DEC47A-seeklogo.com.png" width=65 height=65> |<img alt="aws logo" src="https://seeklogo.com/images/A/amazon-web-services-aws-logo-6C2E3DCD3E-seeklogo.com.png" width=65 height=65>   | <img alt="linux logo" src="https://seeklogo.com/images/L/linux-logo-3793382FC8-seeklogo.com.png" width=65 height=65> |

### Comment :
Spring Boot 3 이상의 버전을 사용하기 위해 자바 17 버전을 사용하였습니다. <br />
Spring JPA Data를 사용하여 Hibernate (ORM)으로 데이터 베이스 환경을 쉽게 구축, 통신 하였습니다. <br />
Spring Security 기반 위에 JWT로 회원의 인증, 인가를 진행하여 stateless하게 서버에 부담을 줄여주었습니다. <br />

AWS의 EC2를 렌탈하여 apache로 웹 서버를 배포하였고, bootWar로 war파일을 생성하여 S3에 업로드 후, EC2로 받아서 실행하여 웹 어플리케이션 서버를 배포를 하였고, RDS로 DB환경을 연결하였습니다.

---

## 진행하며 느낀 점 :
백엔드 과정을 들으며 데이터가 클라이언트에게 제공되는 과정이 궁금하여 '처음부터 끝까지 직접 한 번 만들어보자' 하게 되어 시작 하였습니다. 처음 시작하면서부터 느낀 점은 '아 내가 웹에 대한 이해가 부족하구나' 였고, HTML이 아닌 웹의 동작 원리부터 공부하며 네트워크에 대해 좀 더 자세히 알 수 있는 기회가 되었습니다.