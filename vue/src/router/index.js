import Vue from 'vue'
import Router from 'vue-router'
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading
/* layout */
import Layout from '../views/layout/Layout'

const _import = require('./_import_' + process.env.NODE_ENV)
Vue.use(Router)
export const constantRouterMap = [
  {path: '/login', component: _import('login/index'), hidden: true},
  {path: '/404', component: _import('404'), hidden: true},
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '首页',
    hidden: true,
    children: [{
      path: 'dashboard', component: _import('dashboard/index')
    }]
  }
]
export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})
export const asyncRouterMap = [
  {
    path: '/sys',
    component: Layout,
    redirect: '/sys/',
    name: '系统管理',
    meta: {title: '系统管理', icon: 'table'},
    children: [
      {
        path: 'user',
        name: '用户列表',
        component: _import('sys/user'),
        meta: {title: '用户列表', icon: 'user'},
        menu: 'user'
      },
      {
        path: 'role',
        name: '权限管理',
        component: _import('sys/role'),
        meta: {title: '权限管理', icon: 'password'},
        menu: 'role'
      },
    ]
  },
  /**
   * 用户管理
   */
  {
    path: '/wxUser',
    component: Layout,
    redirect: '/wxUser/',
    name: '用户管理',
    meta: {title: '用户管理', icon: 'user'},
    children: [
      {
        path: 'wxUser',
        name: '小程序管理',
        component: _import('wxUser/wxUser'),
        meta: {title: '小程序管理', icon: 'list'},
        menu: 'wxUser'
      },
      {
        path: 'notice',
        name: '通知推送',
        component: _import('wxUser/notice'),
        meta: {title: '通知推送', icon: 'list'},
        menu: 'notice'
      }
    ]
  },
  /**
   * 平台管理
   */
  {
    path: '/platformManage',
    component: Layout,
    redirect: '/platformManage/',
    name: '平台管理',
    meta: {title: '平台管理', icon: 'mng'},
    children: [
      {
        path: 'banner',
        name: '首页滚动栏',
        component: _import('platformManage/banner'),
        meta: {title: '首页滚动栏', icon: 'list'},
        menu: 'banner'
      },
      {
        path: 'postType',
        name: '帖子类别',
        component: _import('platformManage/postType'),
        meta: {title: '帖子类别', icon: 'list'},
        menu: 'postType'
      },
      {
        path: 'postLabel',
        name: '帖子标签',
        component: _import('platformManage/postLabel'),
        meta: {title: '帖子标签', icon: 'list'},
        menu: 'postLabel'
      },
      {
        path: 'noticeTemplate',
        name: '通知模版',
        component: _import('platformManage/noticeTemplate'),
        meta: {title: '通知模版', icon: 'list'},
        menu: 'noticeTemplate'
      }
    ]
  },
  /**
   * 帖子管理
   */
  {
    path: '/post',
    component: Layout,
    redirect: '/post/',
    name: '帖子管理',
    meta: {title: '帖子管理', icon: 'mng'},
    children: [
      {
        path: 'post',
        name: '帖子管理',
        component: _import('post/post'),
        meta: {title: '帖子管理', icon: 'list'},
        menu: 'post'
      },
      {
        path: 'comment',
        name: '评论管理',
        component: _import('post/comment'),
        meta: {title: '评论管理', icon: 'list'},
        menu: 'comment'
      }
    ]
  },
  /**
   * 数据统计
   */
  {
    path: '/statistics',
    component: Layout,
    redirect: '/statistics/',
    name: '数据统计',
    meta: {title: '数据统计', icon: 'mng'},
    children: [
      {
        path: 'genderStatistics',
        name: '性别统计',
        component: _import('statistics/genderStatistics'),
        meta: {title: '性别统计', icon: 'list'},
        menu: 'genderStatistics'
      },
      {
        path: 'postStatistics',
        name: '帖子统计',
        component: _import('statistics/postStatistics'),
        meta: {title: '帖子统计', icon: 'list'},
        menu: 'postStatistics'
      },
      {
        path: 'resonateStatistics',
        name: '关注统计',
        component: _import('statistics/resonateStatistics'),
        meta: {title: '关注统计', icon: 'list'},
        menu: 'resonateStatistics'
      }, {
        path: 'regionStatistics',
        name: '地区统计',
        component: _import('statistics/regionStatistics'),
        meta: {title: '地区统计', icon: 'list'},
        menu: 'regionStatistics'
      }

    ]
  },

  {path: '*', redirect: '/404', hidden: true}
]
