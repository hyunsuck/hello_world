/**
 * board1.js
 * XMLHttpRequest, fetch => 실행순서.
 */
let page = 1;

// 함수선언식.	- 함수표현식.
// 목록출력
let successCallback = function successCallback(result){
	// 기존목록 지우기.
	document.querySelectorAll('div.reply div.content>ul>li')//
		.forEach(function(item, idx) {
			if(idx > 0){ // truthy, falsy(0, null, '', undefined)
				item.remove();
			}
		});
	// 새로운 목록 출력.
	result.forEach(item => {
		makeRow2(item);
	});
}
//에러 콜백
function errorCallback(err) {
	console.error(err);
}

//페이징 콜백
function pagingCallback(result) {
	// 페이지목록 지우기.
	let target = document.querySelector('nav>ul.pagination');
	target.innerHTML = '';
	console.log(result.totalCnt);
	let totalCnt = result.totalCnt;
	// 첫페이지, 마지막페이지, => 현재페이지로 계산.
	let startPage, endPage;
	// 이전페이지, 이후페이지
	let prev, next;
	endPage = Math.ceil(page / 10) * 10;
	startPage = endPage - 9;
	let realEnd = Math.ceil(totalCnt / 5);
	endPage = endPage > realEnd ? realEnd : endPage;
	prev = startPage == 1 ? false : true;
	next = endPage < realEnd ? true : false;
	
		//이전 페이지
		let prevContent;
		if (!prev) {
			prevContent = `<li class="page-item disabled">
						   <span class="page-link">Previous</span></li>`;
		} else {
			prevContent = `<li class="page-item">
						   <a class="page-link" data-page="${startPage-1}" href="#">Previous</a></li>`
		}
		let prevTarget = document.querySelector('nav>ul.pagination');
		prevTarget.insertAdjacentHTML('beforeend', prevContent);
		

		// 페이지 번호
		for (let p = startPage; p <= endPage; p++) {
				let active = (p == page) ? 'active' : '';
				let html = `<li class="page-item ${active}">
					<a class="page-link" href="#" data-page="${p}">${p}</a></li>`;
				target.insertAdjacentHTML('beforeend', html);
			}

		// 다음 페이지
		let nextContent;
		if (!next) {
		  nextContent = `<li class="page-item disabled"><span class="page-link">Next</span></li>`;
		} else {
		  nextContent = `<li class="page-item">
		    <a class="page-link" data-page="${endPage + 1}" href="#">Next</a></li>`;
		}
		let nextTarget = document.querySelector('nav>ul.pagination');
		nextTarget.insertAdjacentHTML('beforeend', nextContent);
	
	
	// 링크이벤트.
	pageLink();

}
// 삭제함수.
function deleteFnc(rno) {
	let deleteOK = confirm("삭제하겠습니까?");
	if(!deleteOK){
		return;
	}
	svc.removeReply(rno// 삭제할 댓글번호
		, function(result) {
			console.log(result); // {retCode: 'OK/NG'}
			if (result.retCode == 'OK') {
				alert("삭제성공!!");
				// id 속성
				svc.replyList({ bno, page }, successCallback, errorCallback);
				svc.pagingList(bno, pagingCallback, errorCallback);
			}
		 }
		, errorCallback);  
		
}

// 이벤트.
document.querySelector('button.addReply')//
	.addEventListener('click', function(e) {
		//등록.
		console.log('a', replyer, 'b')
		if (replyer == ''){
			alert('로그인하세요.');
			return;
		}
		// bno, replyer, reply: #reply.value속성
		let reply = document.querySelector('#reply').value;
		if (reply == '') {
			alert('댓글을 등록하세요.');
			return;
		}
		svc.addReply({ bno, reply, replyer }// 등록의 첫번째 param
			,function(result) {
				if(result.retCode == 'OK'){
					alert('등록성공!');
					let item = result.retVal; //반환결과값 활용.
					makeRow2(item);
					// 입력값 초기화.
					document.querySelector('#reply').value = '';
					
					page = 1;
					svc.replyList({ bno, page }, successCallback, errorCallback);
					svc.pagingList(bno, pagingCallback, errorCallback);
					
				} else {
					alert('등록실패!');
				}
			}, errorCallback);
		
	})
// 페이지 링크에 이벤트 등록.
function pageLink(){
document.querySelectorAll('div.reply ul a')//
	.forEach(function(atag){
		atag.addEventListener('click', function(e) {
			e.preventDefault(); //이벤트의 기본기능 차단.
			page = atag.dataset.page; // <a>3</a>
			// 댓글목록.
			svc.replyList({ bno, page }, successCallback, errorCallback);
			// 페이징 목록.
			svc.pagingList(bno, pagingCallback, errorCallback);
		})
	});
}

// 목록보여주기.
svc.replyList({ bno,page }, successCallback, errorCallback);

// 페이징목록 보여주기.
svc.pagingList(bno, pagingCallback, errorCallback);

// 댓글정보 -> 화면출력.	
function makeRow2(item){
	let html = `<li>
		      	<span class="col-sm-2">${item.replyNo}</span>
		        <span class="col-sm-5">${item.reply}</span>
		        <span class="col-sm-2">${item.replyer}</span>
		        <span class="col-sm-2"><button onclick="deleteFnc(${item.replyNo})">삭제</button></span>
		      </li>`;
		let templ = document.querySelector('div.content>ul');
		templ.insertAdjacentHTML('beforeend', html);
		
		// 5개 초과 시 마지막 댓글 제거
		let replies = templ.querySelectorAll('li');
		if (replies.length > 6) {
		templ.removeChild(replies[replies.length - 1]);
			
	}
// 동기, 비동기(Asynchronous Javascript And Xml)
setTimeout(function() {
	console.log('1');
	
	setTimeout(function() {
		console.log('2');
		
		setTimeout(function() {
			console.log('3');
		}, 1000);
	}, 1000);
}, 1000);

}
