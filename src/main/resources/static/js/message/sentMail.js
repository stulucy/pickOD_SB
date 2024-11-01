//헤더푸터
$(function () {
$("#header").load("../main/header.html");
});

$(function () {
$("#footer").load("../main/footer.html");
});

// 보낸편지모달기능
$(function () {
  $("#sentmailModal").load("../message/sentmailModal.html");
});

// 전체클릭 기능
let checkAll = document.querySelector('.all');
let checkItem = document.querySelectorAll('.item');


checkAll.addEventListener('click', function() {
  checkItem.forEach(function(e) {
    e.checked = checkAll.checked;
  });
});

checkItem.forEach(function(e) {
  e.addEventListener('click', function() {
      if (!e.checked) {
        checkAll.checked = false;
      } else {
          const allChecked = Array.to(checkItem).every(function(checkItem) {
              return checkItem.checked;
          });
          checkAll.checked = allChecked;
      }
  });
});

//휴지통 이동
const btnDelete = document.querySelector('.btn-delete');
console.log(btnDelete);
const mailboxList = document.querySelectorAll('.mailbox-list');
console.log(mailboxList);

btnDelete.addEventListener('click', function() {
  const checkboxes = document.querySelectorAll('.item');
  console.log(checkboxes);
  checkboxes.forEach((checkbox, index) => {
      if (checkbox.checked) {
          const messageItem = checkbox.closest('.mailbox-list');
          console.log(messageItem);
          messageItem.remove();
          // 받은 쪽지함에서 삭제
          // trashList.appendChild(messageItem);   // 휴지통으로 이동
          checkbox.checked = false; // 체크 상태 초기화
      }
  });
});

// 쪽지띄우기
document.addEventListener('DOMContentLoaded', function() {
  // 모든 mailbox-list 요소들을 가져오기
  var mailboxLists = document.querySelectorAll('.mailbox-list');

  // 각 mailbox-list 요소에 클릭 이벤트 추가
  mailboxLists.forEach(function(mailbox) {
    mailbox.addEventListener('click', function() {

      //체크박스부분은제외
      if (event.target.tagName === 'INPUT' && event.target.type === 'checkbox') {
        return; // 체크박스를 클릭하면 함수 실행을 멈춤
      }

      // 읽으면 편지 읽음표시기능
      var readMail =this.querySelector('.mail-open img');
      readMail.src = '../../img/message/받은편지.png';
      // 클릭한 mailbox-list 안의 mail-to 텍스트 가져오기
      var senderText = this.querySelector('.mail-to').innerText;
      // 클릭한 mailbox-list 안의 mail-content 텍스트 가져오기
      var contentText = this.querySelector('.mail-content').innerText;

      // 모달의 ppl-to 요소에 발신자 텍스트 설정
      document.querySelector('.ppl-to').innerText = senderText;
      // 모달의 nonmodal-textarea에 쪽지 내용 텍스트 설정
      document.querySelector('.nonmodal-textarea').innerText = contentText;

      // 모달 보이기
      document.querySelector('.sentmsg-container').style.display = 'block';
    });
  });

  // 모달 닫기 버튼 클릭 시 모달 숨기기 
  // document.querySelector('.btn-close').addEventListener('click', function() {
  //   document.querySelector('.sentmsg-container').style.display = 'none';
  // });
});



// // 게시물 및 페이지네이션 처리

const mailboxLists = Array.from(document.querySelectorAll('.mailbox-list'));
const MailsPerPage = 10;
let currentPage = 1;
let totalPages;

function displayMails() {
  const postContainer = document.getElementById('mails');
  postContainer.innerHTML = ''; // 현재 표시된 항목 초기화

  const startIndex = (currentPage - 1) * MailsPerPage;
  const endIndex = Math.min(startIndex + MailsPerPage, mailboxLists.length); // 실제 항목 수와 비교하여 인덱스 계산

  // 해당 페이지에 맞는 mailbox-list만 표시
  for (let i = startIndex; i < endIndex; i++) {
    postContainer.appendChild(mailboxLists[i]);
  }
}


function setupPagination() {
  const paginationContainer = document.getElementById('pagination');
  paginationContainer.innerHTML = ''; // 페이지네이션 초기화

  totalPages = Math.ceil(mailboxLists.length / MailsPerPage); // 실제 항목 수로 페이지 수 계산

  const createButton = (pageNum, text) => {
    const button = document.createElement('button');
    button.textContent = text;
    button.disabled = (currentPage === pageNum);
    button.addEventListener('click', () => {
      currentPage = pageNum;
      displayMails(); // 페이지 클릭 시 항목 업데이트
      setupPagination(); // 페이지네이션 버튼 다시 설정
    });
    return button;
  };

  // 이전 페이지 버튼
  if (currentPage > 1) {
    const prevButton = createButton(currentPage - 1, '<');
    paginationContainer.appendChild(prevButton);
  }

  // 페이지 버튼 생성
  for (let i = 1; i <= totalPages; i++) {
    const button = createButton(i, i);
    paginationContainer.appendChild(button);
  }

  // 다음 페이지 버튼
  if (currentPage < totalPages) {
    const nextButton = createButton(currentPage + 1, '>');
    paginationContainer.appendChild(nextButton);
  }
};


  document.addEventListener('DOMContentLoaded', function() {
    setupPagination();
    displayMails();
  });
