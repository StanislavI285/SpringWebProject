

function loadComments() {
    const commentsSection = document.getElementById("comments-section");
    const newsId = document.getElementById("news-id").value;
    commentsSection.innerHTML = '';
    const url = "http://localhost:8080/comments/" + newsId;

    fetch(url).
    then(response => response.json()).
    then(json => json.forEach(c => {
        let comment = createComment(c);
        commentsSection.appendChild(comment);
    }))
}

function addComment() {
    const fromUsername = document.getElementById("from-username").value;
    let commentContent = document.getElementById("comment-input-content");

    const newsId = document.getElementById("news-id").value;
    const csrfToken = document.getElementById("csrf-token").value;
    const url = "http://localhost:8080/comments/add";

    if (!commentContent.value) {
        alert('Comment cannot be empty.')
        return;
    }

// post body data
    const comment = {
        author: fromUsername,
        content: commentContent.value,
        newsId: newsId
    };



// create request object
    const request = new Request(url, {
        method: 'POST',
        credentials:'include',
        body: JSON.stringify(comment),
        headers: {
            'X-CSRF-Token': csrfToken,
            'Content-Type': 'application/json'
        }
    });


    commentContent.value = '';



// pass request object to `fetch()`
    fetch(request)
        .then(res => res.json())
        .then(() => loadComments());
}

function createComment(c) {
    const commentTag = document.createElement("div");
    const contentTag = document.createElement("p");
    const authorTag = document.createElement("strong");
    const authorImg = document.createElement("img");
    const dateAdded = document.createElement("small")

    contentTag.textContent = c.content;
    contentTag.classList.add("comment-content")
    authorTag.textContent = c.author.username;
    authorTag.classList.add("comment-author-name")
    authorImg.setAttribute("src", c.author['imageUrl']);
    dateAdded.textContent = c["dateAdded"];
    authorImg.classList.add("comment-user-avatar");

    commentTag.appendChild(authorImg);
    commentTag.appendChild(authorTag);
    commentTag.appendChild(dateAdded);
    commentTag.appendChild(contentTag);
    commentTag.classList.add("comment")

    return commentTag;
}