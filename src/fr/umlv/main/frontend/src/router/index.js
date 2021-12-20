import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

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
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/dashboard',
    name: 'DashBoard',
    component: () => import('../views/DashBoard.vue')
  },
  {
    path: '/fullcalendar',
    name: 'FullCalendar',
    component: () => import('../views/FullCalendar.vue')
  },
  {
    path: '/connexion',
    name: 'Connexion',
    component: () => import('../views/ConnexionView.vue')
  },
  {
    path: '/createUser',
    name: 'CreateUser',
    component: () => import('../views/CreateUserView.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
