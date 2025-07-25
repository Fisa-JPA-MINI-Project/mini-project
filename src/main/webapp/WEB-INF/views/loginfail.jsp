<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인 실패</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Jua&family=Orbitron:wght@700&display=swap">
<style>
body {
  margin: 0;
  padding: 0;
  min-height: 100vh;
  background: radial-gradient(ellipse at 50% 30%, #3a3a60 0%, #232526 80%);
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: 'Jua', 'Orbitron', 'Noto Sans KR', sans-serif;
}
.stars {
  position: fixed;
  width: 100vw;
  height: 100vh;
  top: 0;
  left: 0;
  pointer-events: none;
  z-index: 0;
}
.fail-container {
  position: relative;
  z-index: 1;
  background: rgba(255,255,255,0.18);
  border-radius: 24px;
  box-shadow: 0 12px 40px 0 rgba(255, 0, 80, 0.18), 0 2px 8px 0 rgba(31, 38, 135, 0.25);
  backdrop-filter: blur(12px);
  -webkit-backdrop-filter: blur(12px);
  border: 2px solid rgba(255,107,107,0.25);
  padding: 56px 48px 40px 48px;
  min-width: 340px;
  display: flex;
  flex-direction: column;
  align-items: center;
  animation: fadeInDown 1.2s cubic-bezier(.68,-0.55,.27,1.55);
}
@keyframes fadeInDown {
  0% { opacity: 0; transform: translateY(-60px);}
  100% { opacity: 1; transform: translateY(0);}
}
.fail-icon {
  font-size: 3.5em;
  color: #ff6b6b;
  margin-bottom: 18px;
  filter: drop-shadow(0 2px 8px #ffb3b3);
  animation: animate__heartBeat 1.2s;
}
.fail-msg {
  color: #fff;
  font-size: 2.1em;
  font-family: 'Orbitron', 'Jua', sans-serif;
  font-weight: 700;
  letter-spacing: 2.5px;
  margin-bottom: 10px;
  text-align: center;
  text-shadow: 0 2px 16px #ff6b6b88, 0 1px 2px #0008;
  line-height: 1.3;
}
.fail-desc {
  color: #ffd6d6;
  font-size: 1.1em;
  margin-bottom: 24px;
  text-align: center;
  letter-spacing: 1px;
  text-shadow: 0 1px 4px #0005;
}
.retry-btn {
  background: linear-gradient(90deg, #ff6b6b 0%, #ffb199 100%);
  color: #fff;
  font-family: 'Jua', 'Noto Sans KR', sans-serif;
  font-size: 1.1em;
  font-weight: bold;
  border: none;
  border-radius: 32px;
  padding: 12px 36px;
  margin-top: 8px;
  box-shadow: 0 2px 12px #ff6b6b33;
  cursor: pointer;
  transition: background 0.2s, transform 0.15s;
}
.retry-btn:hover, .retry-btn:focus {
  background: linear-gradient(90deg, #ffb199 0%, #ff6b6b 100%);
  transform: translateY(-2px) scale(1.04);
  outline: none;
}
@media (max-width: 400px) {
  .fail-container {
    min-width: 92vw;
    padding: 28px 8px 20px 8px;
  }
  .fail-msg {
    font-size: 1.2em;
  }
  .fail-icon {
    font-size: 2.2em;
  }
}
</style>
</head>
<body>
<canvas class="stars"></canvas>
<div class="fail-container animate__animated animate__fadeInDown">
  <div class="fail-icon animate__animated animate__heartBeat">🚫</div>
  <div id="failMsg" class="fail-msg animate__animated"></div>
  <div class="fail-desc">로그인에 실패했습니다.<br>이름이 ${requestScope.name}가 맞는지 확인해주세요!</div>
  <button class="retry-btn" onclick="location.href='/fisa_JPAProject2'">다시 시도하기</button>
</div>
<script>
// 별 떨어지는 애니메이션
const canvas = document.querySelector('.stars');
const ctx = canvas.getContext('2d');
let stars = [];
const STAR_COUNT = 80;
function resizeCanvas() {
  canvas.width = window.innerWidth;
  canvas.height = window.innerHeight;
}
window.addEventListener('resize', resizeCanvas);
resizeCanvas();
function randomStar() {
  return {
    x: Math.random() * canvas.width,
    y: Math.random() * -canvas.height,
    r: Math.random() * 1.8 + 0.8,
    speed: Math.random() * 1.7 + 0.7,
    opacity: Math.random() * 0.5 + 0.5
  };
}
function createStars() {
  stars = [];
  for (let i = 0; i < STAR_COUNT; i++) {
    stars.push(randomStar());
  }
}
createStars();
function drawStars() {
  ctx.clearRect(0, 0, canvas.width, canvas.height);
  ctx.save();
  ctx.shadowColor = '#fff';
  ctx.shadowBlur = 10;
  for (let star of stars) {
    ctx.globalAlpha = star.opacity;
    ctx.beginPath();
    ctx.arc(star.x, star.y, star.r, 0, Math.PI * 2);
    ctx.fillStyle = '#fff';
    ctx.fill();
  }
  ctx.restore();
}
function updateStars() {
  for (let star of stars) {
    star.y += star.speed;
    if (star.y > canvas.height) {
      Object.assign(star, randomStar());
      star.y = 0;
    }
  }
}
function animateStars() {
  drawStars();
  updateStars();
  requestAnimationFrame(animateStars);
}
animateStars();

// 다양한 애니메이션 효과 랜덤 적용
const effects = [
  'animate__bounce',
  'animate__shakeX',
  'animate__wobble',
  'animate__heartBeat',
  'animate__jello',
  'animate__rubberBand',
  'animate__headShake',
  'animate__tada',
  'animate__swing',
  'animate__pulse'
];
function randomEffect() {
  return effects[Math.floor(Math.random() * effects.length)];
}
function showFailEffect() {
  const msg = document.getElementById('failMsg');
  msg.classList.remove(...effects); // 기존 효과 제거
  const effect = randomEffect();
  msg.classList.add(effect);
  msg.addEventListener('animationend', () => {
    msg.classList.remove(effect);
    setTimeout(showFailEffect, 700); // 0.7초 후 새로운 효과 반복
  }, {once:true});
}

// 실패 메시지 동적 표시
window.addEventListener('DOMContentLoaded', () => {
  const msg = document.getElementById('failMsg');
  let paramMsg = decodeURIComponent((location.search.match(/msg=([^&]*)/)||[])[1]||'');
  if(paramMsg && paramMsg.trim() !== '') {
    msg.innerHTML = `<span style="color:#ffb199">${paramMsg}</span> <span style="color:#ff6b6b">실패!</span>`;
  } else {
    msg.innerHTML = `<span style="color:#ff6b6b">로그인 실패!</span>`;
  }
  showFailEffect();
});
</script>
</body>
</html>
