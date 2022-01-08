$('#confirmacaoExclusaoModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) 
  
  var codigo = button.data('codigo');
  var descricao = button.data('descricao');
  
  var modal = $(this);
 
  var form = modal.find('form');
  var action = form.attr('action');
  if(!action.endsWith('/')){
	  action += '/';
  }
  
  form.attr('action', action + codigo);
  modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricao + '</strong>?');
  
  $()
  
})

$(function () {
  $('[rel="tooltip"]').tooltip();
  
	$('.js-atualizar-status').on('click', function(event) {
		event.preventDefault();
		
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		var response = $.ajax({
			url: urlReceber,
			type: 'PUT'
		});
		
		response.done(function(e) {
			window.location.reload();
		});
				
	});
});



//script que adiciona máscara nos campos
$(document).ready(function() {
	$("#valor").mask("#.##0,00", {reverse: true}) 
})

//script de validação de formulários (user) 
$(document).ready(function() {
	$("#form").validate({
		rules : {
			descricao : {
				required : true,
				maxlength : 60,
				minlength : 3
			}
		}
	})
})

