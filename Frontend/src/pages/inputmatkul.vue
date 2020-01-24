<template>
  <q-page>
    <div
class="judul text-h6 q-pa-md bg-grey-2 q-mb-md">
        <q-toolbar-title >
          Please input the courses data
        </q-toolbar-title>
<div class="komentar absolute-top-right q-pt-md q-pr-md">
<q-icon name="school" />
  Courses
</div>
</div>
    <div class="row justify-center q-pt-md">
      <div class="col-md-4 col-xs-8">
        <q-card flat>
          <q-card-section>
            <div class="text-h4 q-pb-md text-center"><b>INPUT COURSES</b></div>
            <q-form
      @submit="onSubmit"
      @reset="onReset"
      class="q-gutter-sm"
    >

      <q-input
        filled
        v-model="kodemk"
        label="Course Code *"
        lazy-rules
        :rules="[
          val => val !== null && val !== '' || 'Please type Your Course Code']"
      />

      <q-input
        filled
        v-model="namamk"
        label="Courses *"
        lazy-rules
        :rules="[ val => val && val.length > 0 || 'Please type Your Nama Courses']"
      />

      <q-input
        filled
        v-model="hari"
        label="Day *"
        lazy-rules
        :rules="[ val => val !== '' && val !== null || 'Please type Your password']"
      />

      <q-input
        filled
        v-model="jam"
        label="Time *"
        lazy-rules
        :rules="[ val => val !== '' && val !== null || 'Please type Your Time']"
      />

      <q-input
        filled
        v-model="ruangan"
        label="Room *"
        lazy-rules
        :rules="[ val => val !== '' && val !== null || 'Please type Your Room']"
      />

      <q-input
        filled
        v-model="dosen"
        label="Lecturer *"
        lazy-rules
        :rules="[ val => val !== '' && val !== null || 'Please type Your Lecturer']"
      />

      <div>
        <q-btn label="Input" type="submit" color="indigo-9" class="q-mt-md"/>
        <q-btn label="Reset" type="reset" color="indigo-9" flat class="q-ml-sm q-mt-md" />
      </div>
    </q-form>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script>
export default {
  data () {
    return {
      kodemk: '',
      namamk: '',
      hari: '',
      jam: '',
      ruangan: '',
      dosen: ''
    }
  },
  methods: {
    onSubmit () {
      this.$axios.post('matakuliah/postmk', {
        kodemk: this.kodemk,
        namamk: this.namamk,
        hari: this.hari,
        jam: this.jam,
        ruangan: this.ruangan,
        dosen: this.dosen
      }).then(res => {
        if (res.data.error) {
          this.showNotif(res.data.pesan, 'negative')
        } else {
          this.showNotif(res.data.pesan, 'Positive')
          this.onReset()
        }
      })
    },
    onReset () {
      this.kodemk = ''
      this.namamk = ''
      this.hari = ''
      this.jam = ''
      this.ruangan = ''
      this.dosen = ''
    },
    showNotif (msg, color) {
      this.$q.notify({
        message: msg,
        color: color
      })
    }
  }
}
</script>
