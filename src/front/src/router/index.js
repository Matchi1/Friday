import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '@/views/About.vue')
  },
  {
    path: '/connexion',
    name: 'Connexion',
    component: () => import('../../src/views/ConnexionView.vue')
  },
  {
    path: '/createUser',
    name: 'CreateUser',
    component: () => import('../../src/views/CreateUserView.vue')
  },
  {
    path: '/dashboard',
    name: 'DashBoard',
    component: () => import('../../src/views/DashBoard.vue')
  },
  {
    path: '/fullcalendar',
    name: 'FullCalendar',
    component: () => import('../../src/views/FullCalendar.vue')
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
