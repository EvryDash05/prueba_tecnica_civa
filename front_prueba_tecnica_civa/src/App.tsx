import React, { useContext, useEffect, useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Home from './pages/Home.tsx'
import RegisterPage from './pages/Register.tsx'
import LoginPage from './pages/Login.tsx'
import CustomHeader from './components/CustomHeader.tsx'
import Credentials from './types/credentials.ts'
import BusPage from './pages/BusPage.tsx'

const App: React.FC = (): JSX.Element => {

  const [credentials, setCredentials] = useState<Credentials | null>(null)

  useEffect(() => {
    const credentials = localStorage.getItem('credentials')
    if (credentials) {
      setCredentials(JSON.parse(credentials))
    }
  }, [])

  const roles = credentials?.roles
  console.log(roles)

  return (
    <Router>
      <CustomHeader />
      <Routes>
        {roles?.includes('ADMIN') || roles?.includes('USER') && <Route path="/admin" element={<BusPage />} />}
        <Route path="/" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
      </Routes>
    </Router>
  )
}

export default App
