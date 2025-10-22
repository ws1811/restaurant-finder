import { Link } from 'react-router-dom'
import '../styles/Home.css'

function Home() {
  return (
    <div className="home-container">
      <h1>🍽️ Restaurant Finder</h1>
      <p className="subtitle">당신을 위한 맞춤 식당 서비스</p>
      
      <div className="menu-grid">
        <Link to="/recommend" className="menu-card">
          <div className="icon">🔍</div>
          <h3>식당 추천</h3>
          <p>AI 기반 맞춤 추천</p>
        </Link>
        
        <Link to="/favorites" className="menu-card">
          <div className="icon">⭐</div>
          <h3>찜한 식당</h3>
          <p>저장된 식당 보기</p>
        </Link>
        
        <Link to="/nearby" className="menu-card">
          <div className="icon">📍</div>
          <h3>주변 식당</h3>
          <p>가까운 식당 찾기</p>
        </Link>
        
        <Link to="/history" className="menu-card">
          <div className="icon">🕐</div>
          <h3>방문 기록</h3>
          <p>내가 간 식당들</p>
        </Link>
      </div>
    </div>
  )
}

export default Home