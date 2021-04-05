const wrapper = document.getElementById("news-wrapper")

function newsPagination() {

    fetch("http://localhost:8080/news/api").then(response => response.json()).then(json => json.forEach(n => {
        createNewsRow(n);
    }))

}

function createNewsRow(news) {

    let {id, title, content, author, category, comments, imageUrl, addedOn, views} = news;
    let d = new Date(addedOn);
    let month = new Intl.DateTimeFormat('default', { month: 'short' }).format(d);
    let date = new Intl.DateTimeFormat('default', { day: '2-digit' }).format(d);
    let hour = new Intl.DateTimeFormat('default', { hour: 'numeric', minute: 'numeric' }).format(d);
    // let min = new Intl.DateTimeFormat('en', { minute: 'numeric' }).format(d);
    let datePrint = `${date}-${month} | ${hour}`;
    console.log()
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



