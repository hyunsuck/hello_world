<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h4>event.jsp</h4>
<div id='calendar'></div>

<style>
  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }
  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }
</style>

<script src='js/index.global.js'></script>
<script>
  document.addEventListener('DOMContentLoaded', async function () {
    let calendarEl = document.getElementById('calendar');
    let events = [];

    try {
      let result = await fetch('eventList.do');
      let result2 = await result.json();
      events = result2;
    } catch (e) {
      console.error('일정 불러오기 실패:', e);
    }

    let calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      initialDate: '2025-04-12',
      navLinks: true,
      selectable: true,
      selectMirror: true,

      select: async function (arg) {
        let title = prompt('Event Title:');
        if (title) {
          let allDay = arg.allDay;
          let start = allDay ? arg.startStr : arg.startStr.substring(0, 19);
          let end = allDay ? arg.endStr : arg.endStr.substring(0, 19);

          let r1 = await fetch('addEvent.do', {
            method: 'post',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: 'title=' + title + '&start=' + start + '&end=' + end
          });
          let r2 = await r1.json();

          if (r2.retCode == 'OK') {
            calendar.addEvent({
              title: title,
              start: arg.start,
              end: arg.end,
              allDay: arg.allDay
            });
          } else {
            alert('등록 실패');
          }
        }
        calendar.unselect();
      },

      eventClick: function (arg) {
        if (confirm('정말 삭제하시겠습니까?')) {
          arg.event.remove();
          // TODO: removeEvent.do 호출해서 DB도 삭제 처리
        }
      },

      editable: true,
      dayMaxEvents: true,
      events: events
    });

    calendar.render();
  });
</script>
