async function newsPagination() {
    let newsList = await fetchNews();
    const newsWrapper = document.getElementById("news-wrapper")
    const paginationElement = document.getElementById('pagination');
    let pageCount;
    let currentPage = 1;
    const newsPerPage = 5;

    displayElements(newsList, newsWrapper, newsPerPage, currentPage);
    setupPagination(newsList, paginationElement, newsPerPage);


    function displayElements(newsList, newsWrapper, newsPerPage, pageNumber) {
        newsWrapper.innerHTML = '';
        pageNumber--;

        let start = newsPerPage * pageNumber;
        let end = start + newsPerPage;
        let paginatedItems = newsList.slice(start, end);
        for (let i = 0; i < paginatedItems.length; i++) {
            let item = paginatedItems[i];
            createNewsRow(newsWrapper, item);
        }
    }

    function setupPagination(newsList, wrapper, newsPerPage) {
        wrapper.innerHTML = '';
        pageCount = Math.ceil(newsList.length / newsPerPage);


        wrapper.appendChild(createPrevBtn())

        let pageBtnsCount = pageCount;

        for (let i = 1; i < pageBtnsCount + 1; i++) {
            let button = paginationButton(i, newsList);
            wrapper.appendChild(button);
        }

        wrapper.appendChild(createNextBtn());


    }

    function paginationButton(page, items) {
        let button = document.createElement('button');
        button.innerText = page;
        button.id = "pageBtn" + page;

        if (currentPage === page) {
            button.classList.add('active');
        }


        button.addEventListener('click', function () {
            currentPage = page;
            document.getElementById("prevBtn").disabled = currentPage === 1;
            document.getElementById("nextBtn").disabled = currentPage === pageCount;
            displayElements(items, newsWrapper, newsPerPage, currentPage);

            let currentBtn = document.querySelector('.page-numbers button.active');
            currentBtn.classList.remove('active');
            button.classList.add('active');
        })

        return button;
    }

    function createPrevBtn() {
        let prevBtn = document.createElement("button");
        prevBtn.id = "prevBtn";
        prevBtn.innerText = '<';
        prevBtn.disabled = true;
        prevBtn.addEventListener('click', function () {
            currentPage--;
            document.getElementById('nextBtn').disabled = false;
            prevBtn.disabled = currentPage === 1;
            displayElements(newsList, newsWrapper, newsPerPage, currentPage);
            let currentBtn = document.querySelector('.page-numbers button.active');
            currentBtn.classList.remove('active');
            document.getElementById("pageBtn" + currentPage).classList.add('active');
        })
        return prevBtn;
    }

    function createNextBtn() {
        let nextBtn = document.createElement("button");
        nextBtn.id = "nextBtn";
        nextBtn.innerText = '>';
        nextBtn.addEventListener('click', function () {
            currentPage++;
            document.getElementById('prevBtn').disabled = false;

            nextBtn.disabled = currentPage === pageCount;
            displayElements(newsList, newsWrapper, newsPerPage, currentPage);
            let currentBtn = document.querySelector('.page-numbers button.active');
            currentBtn.classList.remove('active');
            document.getElementById("pageBtn" + currentPage).classList.add('active');
        })
        return nextBtn;
    }


}


function createNewsRow(wrapper, news) {

    let {id, title, content, category, imageUrl, addedOn} = news;
    let d = new Date(addedOn);
    let month = new Intl.DateTimeFormat('default', {month: 'short'}).format(d);
    let date = new Intl.DateTimeFormat('default', {day: '2-digit'}).format(d);
    let hour = new Intl.DateTimeFormat('default', {hour: 'numeric', minute: 'numeric'}).format(d);
    // let min = new Intl.DateTimeFormat('en', { minute: 'numeric' }).format(d);
    let datePrint = `${date}-${month} | ${hour}`;

    let newDiv = `<div class="row">
                                        <div class="col-sm-4 grid-margin">
                                            <div class="rotate-img">
                                                <img
                                                        alt="banner"
                                                        class="img-fluid"
                                                        src=${imageUrl}
                                                />
                                            </div>
                                        </div>
                                        <div class="col-sm-8 grid-margin">
                                            <a href="/news/${id}">
                                                <h2 class="font-weight-600 mb-2">${title}</h2>
                                                <p class="fs-13 text-muted mb-0">
                                                    <span class="mr-2">${category.name}</span>
                                                    <span style="float: right"
                                                          >${datePrint}</span>
                                                </p>
                                            </a>
                                            <p class="fs-15">${content.toString().substr(0, 200)}</p>
                                        </div>
                                    </div>`;

    wrapper.innerHTML += newDiv;
}

async function fetchNews() {
    const list = [];
    await fetch("http://localhost:8080/news/api").then(response => response.json()).then(json => {
        for (let news of json) {
            list.push(news);
        }
        return list;
    });
    return list;
}



