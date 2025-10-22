import { useState } from 'react'
import './App.css'

function App() {
  const [latitude, setLatitude] = useState('')
  const [longitude, setLongitude] = useState('')
  const [peopleCount, setPeopleCount] = useState(2)
  const [menuPreference, setMenuPreference] = useState('')
  const [recommendation, setRecommendation] = useState('')
  const [loading, setLoading] = useState(false)

  // 현재 위치 가져오기
  const getCurrentLocation = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          setLatitude(position.coords.latitude)
          setLongitude(position.coords.longitude)
          alert('위치를 가져왔습니다!')
        },
        (error) => {
          alert('위치를 가져올 수 없습니다: ' + error.message)
        }
      )
    } else {
      alert('브라우저가 위치 서비스를 지원하지 않습니다.')
    }
  }

  // 추천 받기
  const getRecommendation = async () => {
    if (!latitude || !longitude || !menuPreference) {
      alert('모든 정보를 입력해주세요!')
      return
    }

    setLoading(true)
    setRecommendation('')

    try {
	  const apiUrl = import.meta.env.VITE_API_URL || 'http://localhost:8080';
      const response = await fetch(`${apiUrl}/api/restaurants/recommend`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          latitude: parseFloat(latitude),
          longitude: parseFloat(longitude),
          peopleCount: peopleCount,
          menuPreference: menuPreference
        })
      })

      const data = await response.text()
      setRecommendation(data)
    } catch (error) {
      alert('추천을 가져오는데 실패했습니다: ' + error.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="container">
      <h1>🍽️ 식당 추천 서비스</h1>
      
      <div className="form">
        <div className="form-group">
          <label>위치</label>
          <button onClick={getCurrentLocation} className="btn-location">
            📍 현재 위치 가져오기
          </button>
          <div className="coordinates">
            {latitude && longitude && (
              <span>위도: {latitude}, 경도: {longitude}</span>
            )}
          </div>
        </div>

        <div className="form-group">
          <label>인원</label>
          <input 
            type="number" 
            value={peopleCount}
            onChange={(e) => setPeopleCount(e.target.value)}
            min="1"
            max="20"
          />
        </div>

        <div className="form-group">
          <label>메뉴 선호도</label>
          <input 
            type="text" 
            value={menuPreference}
            onChange={(e) => setMenuPreference(e.target.value)}
            placeholder="예: 매운 음식, 한식, 중식"
          />
        </div>

        <button 
          onClick={getRecommendation} 
          className="btn-submit"
          disabled={loading}
        >
          {loading ? '추천 받는 중...' : '✨ 추천 받기'}
        </button>
      </div>

      {recommendation && (
        <div className="result">
          <h2>추천 결과</h2>
          <pre>{recommendation}</pre>
        </div>
      )}
    </div>
  )
}

export default App// CI/CD test
