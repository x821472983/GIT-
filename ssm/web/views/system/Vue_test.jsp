<html>
<head>
    <title>Title</title>
</head>
<script type="application/javascript" src="/base/js/vue.js"></script>
<body>
<div id="app">
    <ur>
        <li v-for="product in products">
            <input type="number" v-model.number="product.role_id">
            {{ product.name }}
            <span v-if="product.role_id ===20">
                - This is Myself
            </span>
            <button @click="product.role_id +=1">
                +
            </button>
            <button @click="product.role_id -=1">
                -
            </button>
        </li>
    </ur>
</div>
</body>
</html>
<script>
    const app = new Vue({
        el: '#app',
        data:{
            products:[
                {
                    "memo": "4564654654",
                    "role_id": 1,
                    "name": "管理员",
                    "create_user_id": 1,
                    "create_user_name": "admin",
                    "create_time": 1526090449000,
                    "update_user_id": 1,
                    "update_user_name": "admin",
                    "update_time": 1530125490000,
                    "is_deleted": 0,
                    "version": 65
                }, {
                    "memo": null,
                    "role_id": 2,
                    "name": "教师",
                    "create_user_id": 1,
                    "create_user_name": "admin",
                    "create_time": 1528092836000,
                    "update_user_id": 1,
                    "update_user_name": "admin",
                    "update_time": 1529466155000,
                    "is_deleted": 0,
                    "version": 8
                }, {
                    "memo": null,
                    "role_id": 3,
                    "name": "课程顾问",
                    "create_user_id": 1,
                    "create_user_name": "admin",
                    "create_time": 1528092882000,
                    "update_user_id": 1,
                    "update_user_name": "admin",
                    "update_time": 1528338430000,
                    "is_deleted": 0,
                    "version": 2
                }, {
                    "memo": null,
                    "role_id": 4,
                    "name": "市场负责人",
                    "create_user_id": 1,
                    "create_user_name": "admin",
                    "create_time": 1528337553000,
                    "update_user_id": 1,
                    "update_user_name": "admin",
                    "update_time": 1528337553000,
                    "is_deleted": 0,
                    "version": 0
                }, {
                    "memo": null,
                    "role_id": 5,
                    "name": "教师负责人",
                    "create_user_id": 1,
                    "create_user_name": "admin",
                    "create_time": 1528525779000,
                    "update_user_id": 1,
                    "update_user_name": "admin",
                    "update_time": 1528529788000,
                    "is_deleted": 0,
                    "version": 3
                }, {
                    "memo": "",
                    "role_id": 6,
                    "name": "测试员",
                    "create_user_id": 1,
                    "create_user_name": "admin",
                    "create_time": 1531448079000,
                    "update_user_id": 1,
                    "update_user_name": "admin",
                    "update_time": 1531532615000,
                    "is_deleted": 0,
                    "version": 1
                }, {
                    "memo": "66666",
                    "role_id": 20,
                    "name": "梁志华",
                    "create_user_id": 14,
                    "create_user_name": "张三",
                    "create_time": 1535161304000,
                    "update_user_id": 0,
                    "update_user_name": null,
                    "update_time": null,
                    "is_deleted": 0,
                    "version": 0
                }
            ]
        },

          /*  [],*/
    })
</script>
