package dev.demax.services;

import java.lang.reflect.Type;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.demax.dtos.ActionDto;
import dev.demax.dtos.DashboardDto;
import dev.demax.dtos.ProfileDto;
import dev.demax.entities.Action;
import dev.demax.entities.User;
import dev.demax.finders.ActionFinder;
import dev.demax.finders.ProductFinder;
import dev.demax.finders.ProjectFinder;
import dev.demax.finders.UserFinder;

@Component
@Transactional
public class UserService implements UserDetailsService {
	private final UserFinder userFinder;
	private final ActionFinder actionFinder;
	private final ProductFinder productFinder;
	private final ProjectFinder projectFinder;
	private final ModelMapper modelMapper;
	
	@Autowired
	public UserService(UserFinder userFinder, ActionFinder actionFinder, ProductFinder productFinder, ProjectFinder projectFinder,
					ModelMapper modelMapper) {
		this.userFinder = userFinder;
		this.actionFinder = actionFinder;
		this.productFinder = productFinder;
		this.projectFinder = projectFinder;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userFinder.getByEmail(username);
		if (user == null) { 
			throw new UsernameNotFoundException("No such user.");
		}

		return user;
	}

	public DashboardDto getDashboard(String email) {
		User user = userFinder.getByEmail(email);
		DashboardDto dto = new DashboardDto();
		Set<Action> actions = actionFinder.getLastFifteenActions(user.getId());
		Type targetSetType = new TypeToken<Set<ActionDto>>() {
		}.getType();
		Set<ActionDto> actionDtos = modelMapper.map(actions, targetSetType);

		dto.setFinishedProductsCount(productFinder.getFinishedProductsCount());
		dto.setFinishedProjectsCount(projectFinder.getFinishedProjectsCount());
		dto.setNewProjectsCount(projectFinder.getNewProjectsCount());
		dto.setProductsInProgressCount(productFinder.getProductsInProgressCount());
		dto.setLoggedInUserFirstName(user.getFirstName());
		dto.setLastFifteenActions(actionDtos);

		return dto;
	}

	public ProfileDto getProfile(String email) {
		User user = this.userFinder.getByEmail(email);
		ProfileDto dto = modelMapper.map(user, ProfileDto.class);
		return dto;
	}
}
