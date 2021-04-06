async function newsPagination() {
    let newsList = await fetchNews();
    const newsWrapper = document.getElementById("news-wrapper")
    const paginationElement = document.getElementById('pagination');

    let currentPage = 1;
    const newsPerPage = 5;

    displayElements(newsList, newsWrapper, newsPerPage, currentPage);
    setupPagination(newsList, paginationElement, newsPerPage);


    function displayElements(newsList, wrapper, rowsPerPage, pageNumber) {
        wrapper.innerHTML = '';
        pageNumber--;

        let start = rowsPerPage * pageNumber;
        let end = start + rowsPerPage;
        let paginatedItems = newsList.slice(start, end);
        for (let i = 0; i < paginatedItems.length; i++) {
            let item = paginatedItems[i];
            createNewsRow(wrapper, item);
        }
    }

    function setupPagination(newsList, wrapper, rowsPerPage) {
        wrapper.innerHTML = '';
        let pageCount = Math.ceil(newsList.length / rowsPerPage);
        for (let i = 1; i < pageCount + 1; i++) {
           let button = paginationButton(i, newsList);
           wrapper.appendChild(button);
        }
    }

    function paginationButton(page, items) {
        let button = document.createElement('button');
        button.innerText = page;

        if (currentPage === page) {
            button.classList.add('active');
        }


        button.addEventListener('click', function () {
            currentPage = page;
            displayElements(items, newsWrapper, newsPerPage, currentPage);

            let currentBtn = document.querySelector('.page-numbers button.active');
            currentBtn.classList.remove('active');
            button.classList.add('active');
        })

        return button;
    }

}


function createNewsRow(wrapper, news) {

    let {id, title, content, author, category, comments, imageUrl, addedOn, views} = news;
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
    console.log(list);
    return list;

}



