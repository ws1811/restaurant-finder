import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Home from './components/Home'
import Recommend from './components/Recommend'
import './App.css'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/recommend" element={<Recommend />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App