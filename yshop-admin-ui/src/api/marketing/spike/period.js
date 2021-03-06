import request from '@/utils/request'

export function page(pageNum, pageSize, queryParams) {
  return request({
    url: '/marketing/spikePeriod/pageNum/' + pageNum + "/pageSize/" + pageSize,
    method: 'get',
    params: queryParams
  });
}

export function postObj(obj) {
  return request({
    url: '/marketing/spikePeriod',
    method: 'post',
    data: obj
  });
}

export function getObj(id) {
  return request({
    url: '/marketing/spikePeriod/' + id,
    method: 'get'
  })
}

export function putObj(id, obj) {
  return request({
    url: '/marketing/spikePeriod/' + id,
    method: 'put',
    data: obj
  })
}

export function delObj(ids) {
  return request({
    url: '/marketing/spikePeriod/' + ids,
    method: 'delete'
  })
}


export function updateStatus(id, status) {
  return request({
    url: '/marketing/spikePeriod/id/' + id+'/status/'+status,
    method: 'put'
  })
}

export function list() {
  return request({
    url: '/marketing/spikePeriod/list',
    method: 'get'
  })
}

