import { useEffect, useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import BusPage from './pages/BusPage.tsx'
import RegisterPage from './pages/Register.tsx'
import LoginPage from './pages/Login.tsx'
import Credentials from './types/credentials.ts'
import '../public/css/customHeader.css'

const App: React.FC = (): JSX.Element => {

  const [credentials, setCredentials] = useState<Credentials | null>(null)

  useEffect(() => {
    const credentials = localStorage.getItem('credentials')
    if (credentials) {
      setCredentials(JSON.parse(credentials))
    }
  }, [])

  const logout = () => {
    localStorage.clear()
    window.location.reload()
  }

  const roles = credentials?.roles

  return (
    <Router>
      <header className="header">
        <nav className="nav">
          {(roles?.includes('USER') || roles?.includes('ADMIN')) &&
            <a href="/buses" className="nav-link">Lista de buses</a>
          }
          {(!roles) &&
            <a href="/" className="nav-link">Iniciar Sesión</a>
          }
          {(!roles) &&
            <a href="/register" className="nav-link">Registro</a>
          }
          {(roles?.includes('USER') || roles?.includes('ADMIN')) &&
            <a href="/" className='nav-link' onClick={logout}>Cerrar Sesión</a>
          }
        </nav>
      </header>

      <Routes>
        {(roles?.includes('ADMIN') || roles?.includes('USER')) &&
          <Route path="/buses" element={<BusPage />} />
        }
        {(!roles) &&
          <Route path="/" element={<LoginPage />} />
        }
        {(!roles) &&
          <Route path="/register" element={<RegisterPage />} />
        }
      </Routes>
    </Router>
  )
}

export default App
