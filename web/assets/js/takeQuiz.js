function cal(timer) {
    let minutes = parseInt(timer / 60, 10);
    let seconds = parseInt(timer % 60, 10);

    minutes = minutes < 10 ? "0" + minutes : minutes;
    seconds = seconds < 10 ? "0" + seconds : seconds;

    document.querySelector('#timer').textContent = minutes + ":" + seconds;
}

function startTime(duration) {
    let timer = duration;
    cal(timer);
    let intervalCount = setInterval(function() {
        cal(timer);

        if (--timer < 0) {
	    document.getElementById('submit').submit();
            clearInterval(intervalCount);
        }
    }, 1000);
}