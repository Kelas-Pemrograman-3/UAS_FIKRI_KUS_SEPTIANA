const routes = [
  {
    path: '/',
    component: () => import('layouts/guest.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/login.vue')
      }
    ]
  },
  {
    path: '/register',
    component: () => import('layouts/guest.vue'),
    children: [
      { path: '', component: () => import('pages/register.vue') }
    ]
  },
  {
    path: '/index',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/index.vue') }
    ]
  },
  {
    path: '/inputdosen',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/inputdosen.vue') }
    ]
  },
  {
    path: '/lihatdosen',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/lihatdosen.vue') }
    ]
  },
  {
    path: '/inputmhs',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/inputmhs.vue') }
    ]
  },
  {
    path: '/lihatmhs',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/lihatmhs.vue') }
    ]
  },
  {
    path: '/inputuang',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/inputuang.vue') }
    ]
  },
  {
    path: '/lihatuang',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/lihatuang.vue') }
    ]
  },
  {
    path: '/inputmatkul',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/inputmatkul.vue') }
    ]
  },
  {
    path: '/lihatmatkul',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/lihatmatkul.vue') }
    ]
  }
]
// Always leave this as last one
if (process.env.MODE !== 'ssr') {
  routes.push({
    path: '*',
    component: () => import('pages/Error404.vue')
  })
}

export default routes
