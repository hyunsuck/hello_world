/**
 *  board2.js
 */
const table = new DataTable('#example', {
    ajax: 'replyListDatatable.do?bno='+bno,
    columns: [
        { data: 'REPLY_NO' },
        { data: 'REPLY' },
        { data: 'REPLYER' },
        { data: 'REPLY_DATE' },
    ],
	lengthMenu: [
	    [5, 10, 25, 50, -1],
	    [5, 10, 25, 50, 'All']
	], 
	order: [[0, 'desc']]
});
// 1.등록
function addNewRow() {
	// 사용자가 입력한 댓글 내용
	const replyContent = document.querySelector('#addreplyInput').value.trim();
	// 서버에 댓글 등록 요청
	fetch('addReply.do?bno=' + bno + '&replyer=' + replyer + '&reply=' + encodeURIComponent(replyContent))
		.then(response => response.json())
		.then(result => {
	console.log('등록결과:', result);
	if (result.retCode === 'OK') {
		const newReply = result.retVal;
		// 테이블 최상단에 새 댓글 추가
		table.row
		 .add({
			REPLY_NO: newReply.replyNo,
		    REPLY: newReply.reply,
		    REPLYER: newReply.replyer,
		    REPLY_DATE: newReply.replyDate
		 })
		 .draw(false); // 페이지 유지
		 document.querySelector('#addreplyInput').value = ''; // 입력창 초기화
		 } else {
			alert('등록 실패');
		 }
	 })
		 .catch(err => {
		 console.error('에러 발생:', err);
		 alert('서버 오류');
	});
}
 
 
document.querySelector('#addRow')//
	.addEventListener('click', addNewRow);
 

//2. 삭제			
let rno;
table.on('click', 'tbody tr', (e) => {
    // 댓글 번호 출력 (선택 시 콘솔에 표시)
    console.log(e.currentTarget.children[0].innerHTML); // ← 오타 수정: cuurentTarget → currentTarget

    let classList = e.currentTarget.classList;

    // 선택 클래스 토글
    if (classList.contains('selected')) {
        classList.remove('selected');
    } else {
        table.rows('.selected').nodes().each((row) => row.classList.remove('selected'));
        classList.add('selected');
    }
});

// 2. 삭제 버튼 클릭 시 이벤트 처리
document.querySelector('#button') // id="button"인 삭제 버튼
    .addEventListener('click', function () {
        // 선택된 행의 데이터
        const selectedData = table.row('.selected').data();

        if (!selectedData) {
            alert('삭제할 댓글을 선택하세요.');
            return;
        }

        // 서버에 삭제 요청
        fetch('removeReply.do?rno=' + selectedData.REPLY_NO) // ★ 쿼리스트링 이름은 서버와 일치 (rno)
            .then(res => res.json())
            .then(result => {
                if (result.retCode === 'OK') {
                    // 테이블에서 삭제 후 갱신
                    table.row('.selected').remove().draw(false);
                } else {
                    alert('삭제 실패!');
                }
            })
            .catch(err => {
                console.error(err);
                alert('서버 오류 발생');
            });
    });

// end of file.