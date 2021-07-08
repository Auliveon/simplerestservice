document.addEventListener("DOMContentLoaded", function () {

});

function addFormEvent(event) {

    event.preventDefault();

    let formData = new FormData(event.target);

    let request = new Request(event.target.action, {
        method: 'POST',
        body: formData,
        enctype: 'multipart/form-data'
    });

    fetch(request).then(() => {
        let modal = bootstrap.Modal.getInstance(document.getElementById('addModal'))
        modal.hide();
        reloadTable();
    });
}

function showModal() {
    fetch('/getmodal')
        .then(response => response.text())
        .then(response => {
            document.querySelector("#addModal").innerHTML = response;
        })
        .then(() => {
            let modal = new bootstrap.Modal(document.getElementById('addModal'), {});
            modal.show();
        });
}

function reloadTable() {

    let table = document.querySelector(`tbody`);

    table.innerHTML = ``;

    fetch('getallemployees', {
        method: 'POST'
    })
        .then((response) => response.json())
        .then(response => {
            response.forEach((elem) => {
                fetch(`/image?pid=${elem.pid}`)
                    .then(response => response.blob())
                    .then(response => {
                        let image = URL.createObjectURL(response);
                        table.insertAdjacentHTML('beforeend', `<td><img src=${image} width="30" height="30"></td><td>${elem.name}</td> <td>${elem.age}</td>`);
                    })
            })
        });
}